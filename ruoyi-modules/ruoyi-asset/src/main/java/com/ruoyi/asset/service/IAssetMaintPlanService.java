package com.ruoyi.asset.service;

import com.ruoyi.asset.domain.entity.AssetMaintPlan;
import com.ruoyi.asset.domain.vo.AssetMaintPlanVO;
import com.ruoyi.asset.mapper.AssetMaintPlanMapper.MaintPlanQuery;

import java.util.List;

/**
 * Maintenance Plan Service Interface
 */
public interface IAssetMaintPlanService {

    /**
     * Query maintenance plan list with filters
     *
     * @param query query parameters
     * @return list of maintenance plans
     */
    List<AssetMaintPlanVO> selectMaintPlanList(MaintPlanQuery query);

    /**
     * Query maintenance plan detail by ID
     *
     * @param id plan ID
     * @return plan detail with asset IDs
     */
    AssetMaintPlanVO selectMaintPlanById(Long id);

    /**
     * Create new maintenance plan
     *
     * @param plan     plan entity
     * @param assetIds list of asset IDs
     * @return plan ID
     */
    Long insertMaintPlan(AssetMaintPlan plan, List<Long> assetIds);

    /**
     * Update maintenance plan
     *
     * @param plan     plan entity
     * @param assetIds list of asset IDs (null to skip updating)
     * @return affected rows
     */
    int updateMaintPlan(AssetMaintPlan plan, List<Long> assetIds);

    /**
     * Delete maintenance plan by ID
     *
     * @param id plan ID
     * @return affected rows
     */
    int deleteMaintPlanById(Long id);

    /**
     * Batch delete maintenance plans
     *
     * @param ids plan IDs
     * @return affected rows
     */
    int deleteMaintPlanByIds(Long[] ids);

    /**
     * Enable plan
     *
     * @param id plan ID
     * @return affected rows
     */
    int enablePlan(Long id);

    /**
     * Disable plan
     *
     * @param id plan ID
     * @return affected rows
     */
    int disablePlan(Long id);

    /**
     * Process all pending plans (for scheduled job)
     * Generates maintenance orders for plans where next_generate_time has passed
     */
    void processScheduledPlans();
}
