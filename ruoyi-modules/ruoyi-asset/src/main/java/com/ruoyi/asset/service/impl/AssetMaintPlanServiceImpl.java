package com.ruoyi.asset.service.impl;

import com.ruoyi.asset.domain.entity.AssetMaintPlan;
import com.ruoyi.asset.domain.entity.AssetMaintPlanAsset;
import com.ruoyi.asset.domain.entity.AssetMaintOrder;
import com.ruoyi.asset.domain.vo.AssetMaintPlanVO;
import com.ruoyi.asset.mapper.AssetMaintPlanAssetMapper;
import com.ruoyi.asset.mapper.AssetMaintPlanMapper;
import com.ruoyi.asset.mapper.AssetMaintPlanMapper.MaintPlanQuery;
import com.ruoyi.asset.service.IAssetMaintPlanService;
import com.ruoyi.asset.service.rule.MaintOrderWorkflowService;
import com.ruoyi.common.datascope.annotation.DataScope;
import com.ruoyi.common.security.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Maintenance Plan Service Implementation
 */
@Service
public class AssetMaintPlanServiceImpl implements IAssetMaintPlanService {

    private static final Logger log = LoggerFactory.getLogger(AssetMaintPlanServiceImpl.class);

    public static final String STATUS_ENABLED = "ENABLED";
    public static final String STATUS_DISABLED = "DISABLED";

    public static final String CYCLE_MONTHLY = "MONTHLY";
    public static final String CYCLE_QUARTERLY = "QUARTERLY";
    public static final String CYCLE_YEARLY = "YEARLY";

    @Autowired
    private AssetMaintPlanMapper maintPlanMapper;

    @Autowired
    private AssetMaintPlanAssetMapper maintPlanAssetMapper;

    @Autowired
    private MaintOrderWorkflowService workflowService;

    @Override
    @DataScope(projectAlias = "mp", deptAlias = "mp", userAlias = "mp")
    public List<AssetMaintPlanVO> selectMaintPlanList(MaintPlanQuery query) {
        return maintPlanMapper.selectMaintPlanList(query);
    }

    @Override
    public AssetMaintPlanVO selectMaintPlanById(Long id) {
        AssetMaintPlanVO vo = maintPlanMapper.selectMaintPlanById(id);
        if (vo != null) {
            // Load associated asset IDs
            List<Long> assetIds = maintPlanAssetMapper.selectAssetIdsByPlanId(id);
            vo.setAssetIds(assetIds);
        }
        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertMaintPlan(AssetMaintPlan plan, List<Long> assetIds) {
        // Set default status
        if (plan.getStatus() == null) {
            plan.setStatus(STATUS_DISABLED);
        }

        // Calculate next generate time based on cycle type
        if (plan.getNextGenerateTime() == null && STATUS_ENABLED.equals(plan.getStatus())) {
            plan.setNextGenerateTime(calculateNextGenerateTime(plan.getCycleType(), new Date()));
        }

        // Set audit fields
        plan.setCreateBy(getCurrentUsername());
        plan.setCreateTime(new Date());

        maintPlanMapper.insertMaintPlan(plan);

        // Save asset relations
        if (assetIds != null && !assetIds.isEmpty()) {
            saveAssetRelations(plan.getId(), assetIds);
        }

        return plan.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateMaintPlan(AssetMaintPlan plan, List<Long> assetIds) {
        plan.setUpdateBy(getCurrentUsername());
        plan.setUpdateTime(new Date());

        int rows = maintPlanMapper.updateMaintPlan(plan);

        // Update asset relations if provided
        if (assetIds != null) {
            maintPlanAssetMapper.deleteByPlanId(plan.getId());
            if (!assetIds.isEmpty()) {
                saveAssetRelations(plan.getId(), assetIds);
            }
        }

        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteMaintPlanById(Long id) {
        // Delete relations first
        maintPlanAssetMapper.deleteByPlanId(id);
        return maintPlanMapper.deleteMaintPlanById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteMaintPlanByIds(Long[] ids) {
        int count = 0;
        for (Long id : ids) {
            count += deleteMaintPlanById(id);
        }
        return count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int enablePlan(Long id) {
        AssetMaintPlanVO vo = maintPlanMapper.selectMaintPlanById(id);
        if (vo == null) {
            throw new RuntimeException("Plan not found: " + id);
        }

        AssetMaintPlan update = new AssetMaintPlan();
        update.setId(id);
        update.setStatus(STATUS_ENABLED);
        update.setUpdateBy(getCurrentUsername());
        update.setUpdateTime(new Date());

        // Set next generate time if not set
        if (vo.getNextGenerateTime() == null) {
            update.setNextGenerateTime(calculateNextGenerateTime(vo.getCycleType(), new Date()));
        }

        return maintPlanMapper.updateMaintPlan(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int disablePlan(Long id) {
        AssetMaintPlan update = new AssetMaintPlan();
        update.setId(id);
        update.setStatus(STATUS_DISABLED);
        update.setUpdateBy(getCurrentUsername());
        update.setUpdateTime(new Date());
        return maintPlanMapper.updateMaintPlan(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void processScheduledPlans() {
        Date now = new Date();
        List<AssetMaintPlan> plans = maintPlanMapper.selectPlansForGeneration(now);

        log.info("Processing {} scheduled maintenance plans", plans.size());

        for (AssetMaintPlan plan : plans) {
            try {
                processOnePlan(plan);
            } catch (Exception e) {
                log.error("Failed to process plan {}: {}", plan.getId(), e.getMessage(), e);
            }
        }
    }

    // Private helpers

    private void processOnePlan(AssetMaintPlan plan) {
        // Get assets associated with this plan
        List<Long> assetIds = maintPlanAssetMapper.selectAssetIdsByPlanId(plan.getId());

        if (assetIds.isEmpty()) {
            log.warn("Plan {} has no assets, skipping", plan.getId());
        } else {
            // Create maintenance order for each asset
            for (Long assetId : assetIds) {
                createMaintOrderForAsset(plan, assetId);
            }
        }

        // Update next generate time
        Date nextTime = calculateNextGenerateTime(plan.getCycleType(), new Date());
        maintPlanMapper.updateNextGenerateTime(plan.getId(), nextTime);

        log.info("Plan {} processed. Created {} orders. Next generate time: {}",
                plan.getId(), assetIds.size(), nextTime);
    }

    private void createMaintOrderForAsset(AssetMaintPlan plan, Long assetId) {
        AssetMaintOrder order = new AssetMaintOrder();
        order.setAssetId(assetId);
        order.setProjectId(plan.getProjectId());
        order.setOrderType("MAINTENANCE");  // Scheduled maintenance type
        order.setTitle("Scheduled Maintenance - " + plan.getPlanName());
        order.setDescription("Auto-generated from maintenance plan: " + plan.getPlanName());
        order.setPriority("NORMAL");

        // Set require finish time based on cycle
        order.setRequireFinishTime(calculateRequireFinishTime(plan.getCycleType()));

        // Create order via workflow service (system user as operator)
        workflowService.createOrder(order, 1L);
    }

    private void saveAssetRelations(Long planId, List<Long> assetIds) {
        List<AssetMaintPlanAsset> relations = new ArrayList<>();
        for (Long assetId : assetIds) {
            relations.add(new AssetMaintPlanAsset(planId, assetId));
        }
        maintPlanAssetMapper.batchInsert(relations);
    }

    private Date calculateNextGenerateTime(String cycleType, Date fromDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fromDate);

        switch (cycleType) {
            case CYCLE_MONTHLY:
                cal.add(Calendar.MONTH, 1);
                cal.set(Calendar.DAY_OF_MONTH, 1);
                break;
            case CYCLE_QUARTERLY:
                cal.add(Calendar.MONTH, 3);
                cal.set(Calendar.DAY_OF_MONTH, 1);
                break;
            case CYCLE_YEARLY:
                cal.add(Calendar.YEAR, 1);
                cal.set(Calendar.MONTH, Calendar.JANUARY);
                cal.set(Calendar.DAY_OF_MONTH, 1);
                break;
            default:
                // Default to monthly
                cal.add(Calendar.MONTH, 1);
                cal.set(Calendar.DAY_OF_MONTH, 1);
        }

        // Set to start of day
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    private Date calculateRequireFinishTime(String cycleType) {
        Calendar cal = Calendar.getInstance();

        // Give reasonable time to complete based on cycle
        switch (cycleType) {
            case CYCLE_MONTHLY:
                cal.add(Calendar.DAY_OF_MONTH, 7);  // 1 week
                break;
            case CYCLE_QUARTERLY:
                cal.add(Calendar.DAY_OF_MONTH, 14); // 2 weeks
                break;
            case CYCLE_YEARLY:
                cal.add(Calendar.DAY_OF_MONTH, 30); // 1 month
                break;
            default:
                cal.add(Calendar.DAY_OF_MONTH, 7);
        }

        return cal.getTime();
    }

    private String getCurrentUsername() {
        try {
            return SecurityUtils.getUsername();
        } catch (Exception e) {
            return "system";
        }
    }
}
