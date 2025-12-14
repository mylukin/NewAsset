package com.ruoyi.asset.controller;

import com.ruoyi.asset.domain.entity.AssetMaintPlan;
import com.ruoyi.asset.domain.vo.AssetMaintPlanVO;
import com.ruoyi.asset.mapper.AssetMaintPlanMapper.MaintPlanQuery;
import com.ruoyi.asset.service.IAssetMaintPlanService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.domain.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.annotation.Log.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Maintenance Plan Controller
 */
@Api(tags = "Maintenance Plan Management")
@RestController
@RequestMapping("/asset/maint/plan")
public class AssetMaintPlanController extends BaseController {

    @Autowired
    private IAssetMaintPlanService maintPlanService;

    /**
     * Query maintenance plan list
     */
    @ApiOperation("Query maintenance plan list")
    @PreAuthorize("@ss.hasPermi('asset:maint:plan:list')")
    @GetMapping("/list")
    public TableDataInfo list(MaintPlanQuery query) {
        startPage();
        List<AssetMaintPlanVO> list = maintPlanService.selectMaintPlanList(query);
        return getDataTable(list);
    }

    /**
     * Get maintenance plan detail
     */
    @ApiOperation("Get maintenance plan detail")
    @PreAuthorize("@ss.hasPermi('asset:maint:plan:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(
            @ApiParam(value = "Plan ID", required = true)
            @PathVariable Long id) {
        AssetMaintPlanVO detail = maintPlanService.selectMaintPlanById(id);
        return success(detail);
    }

    /**
     * Create maintenance plan
     */
    @ApiOperation("Create maintenance plan")
    @PreAuthorize("@ss.hasPermi('asset:maint:plan:add')")
    @Log(title = "Maintenance Plan", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(
            @ApiParam(value = "Plan data", required = true)
            @Validated @RequestBody MaintPlanCreateRequest request) {
        Long id = maintPlanService.insertMaintPlan(request.getPlan(), request.getAssetIds());
        return success(id);
    }

    /**
     * Update maintenance plan
     */
    @ApiOperation("Update maintenance plan")
    @PreAuthorize("@ss.hasPermi('asset:maint:plan:edit')")
    @Log(title = "Maintenance Plan", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(
            @ApiParam(value = "Plan data", required = true)
            @Validated @RequestBody MaintPlanUpdateRequest request) {
        return toAjax(maintPlanService.updateMaintPlan(request.getPlan(), request.getAssetIds()));
    }

    /**
     * Delete maintenance plans
     */
    @ApiOperation("Delete maintenance plans")
    @PreAuthorize("@ss.hasPermi('asset:maint:plan:remove')")
    @Log(title = "Maintenance Plan", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(
            @ApiParam(value = "Plan IDs", required = true)
            @PathVariable Long[] ids) {
        return toAjax(maintPlanService.deleteMaintPlanByIds(ids));
    }

    /**
     * Enable maintenance plan
     */
    @ApiOperation("Enable maintenance plan")
    @PreAuthorize("@ss.hasPermi('asset:maint:plan:edit')")
    @Log(title = "Maintenance Plan", businessType = BusinessType.UPDATE)
    @PutMapping("/enable/{id}")
    public AjaxResult enable(
            @ApiParam(value = "Plan ID", required = true)
            @PathVariable Long id) {
        return toAjax(maintPlanService.enablePlan(id));
    }

    /**
     * Disable maintenance plan
     */
    @ApiOperation("Disable maintenance plan")
    @PreAuthorize("@ss.hasPermi('asset:maint:plan:edit')")
    @Log(title = "Maintenance Plan", businessType = BusinessType.UPDATE)
    @PutMapping("/disable/{id}")
    public AjaxResult disable(
            @ApiParam(value = "Plan ID", required = true)
            @PathVariable Long id) {
        return toAjax(maintPlanService.disablePlan(id));
    }

    /**
     * Manually trigger plan processing (admin only)
     */
    @ApiOperation("Manually trigger plan processing")
    @PreAuthorize("@ss.hasPermi('asset:maint:plan:execute')")
    @Log(title = "Maintenance Plan", businessType = BusinessType.OTHER)
    @PostMapping("/execute")
    public AjaxResult executeScheduledPlans() {
        maintPlanService.processScheduledPlans();
        return success("Scheduled plans processed");
    }

    /**
     * Export maintenance plan list
     */
    @ApiOperation("Export maintenance plan list")
    @PreAuthorize("@ss.hasPermi('asset:maint:plan:export')")
    @Log(title = "Maintenance Plan", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(MaintPlanQuery query) {
        List<AssetMaintPlanVO> list = maintPlanService.selectMaintPlanList(query);
        // TODO: Implement Excel export using ExcelUtil
        return success("Export functionality to be implemented");
    }

    // Request DTOs

    public static class MaintPlanCreateRequest {
        private AssetMaintPlan plan;
        private List<Long> assetIds;

        public AssetMaintPlan getPlan() {
            return plan;
        }

        public void setPlan(AssetMaintPlan plan) {
            this.plan = plan;
        }

        public List<Long> getAssetIds() {
            return assetIds;
        }

        public void setAssetIds(List<Long> assetIds) {
            this.assetIds = assetIds;
        }
    }

    public static class MaintPlanUpdateRequest {
        private AssetMaintPlan plan;
        private List<Long> assetIds;

        public AssetMaintPlan getPlan() {
            return plan;
        }

        public void setPlan(AssetMaintPlan plan) {
            this.plan = plan;
        }

        public List<Long> getAssetIds() {
            return assetIds;
        }

        public void setAssetIds(List<Long> assetIds) {
            this.assetIds = assetIds;
        }
    }
}
