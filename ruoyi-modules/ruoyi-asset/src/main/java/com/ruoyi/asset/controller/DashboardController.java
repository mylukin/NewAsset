package com.ruoyi.asset.controller;

import com.ruoyi.asset.domain.vo.DashboardVO;
import com.ruoyi.asset.service.IDashboardService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Dashboard Controller
 * Provides aggregated statistics for different role-based dashboards
 */
@Api(tags = "Dashboard Management")
@RestController
@RequestMapping("/asset/dashboard")
public class DashboardController extends BaseController {

    @Autowired
    private IDashboardService dashboardService;

    /**
     * Get management dashboard data
     * For executives and managers - high-level KPIs
     */
    @ApiOperation("Get management dashboard data")
    @PreAuthorize("@ss.hasPermi('asset:dashboard:management')")
    @GetMapping("/management")
    public AjaxResult getManagementDashboard(
            @ApiParam(value = "Project ID (optional)")
            @RequestParam(required = false) Long projectId) {
        DashboardVO dashboard = dashboardService.getManagementDashboard(projectId);
        return success(dashboard);
    }

    /**
     * Get operations dashboard data
     * For operations staff - maintenance orders, alerts
     */
    @ApiOperation("Get operations dashboard data")
    @PreAuthorize("@ss.hasPermi('asset:dashboard:ops')")
    @GetMapping("/ops")
    public AjaxResult getOpsDashboard(
            @ApiParam(value = "Project ID (optional)")
            @RequestParam(required = false) Long projectId) {
        DashboardVO dashboard = dashboardService.getOpsDashboard(projectId);
        return success(dashboard);
    }

    /**
     * Get asset manager dashboard data
     * For asset managers - portfolio data
     */
    @ApiOperation("Get asset manager dashboard data")
    @PreAuthorize("@ss.hasPermi('asset:dashboard:manager')")
    @GetMapping("/manager")
    public AjaxResult getAssetManagerDashboard(
            @ApiParam(value = "Project ID (optional)")
            @RequestParam(required = false) Long projectId) {
        DashboardVO dashboard = dashboardService.getAssetManagerDashboard(projectId);
        return success(dashboard);
    }

    /**
     * Get statistics for a specific asset type
     */
    @ApiOperation("Get statistics for a specific asset type")
    @PreAuthorize("@ss.hasPermi('asset:dashboard:query')")
    @GetMapping("/type/{assetType}")
    public AjaxResult getAssetTypeStats(
            @ApiParam(value = "Asset type code", required = true)
            @PathVariable String assetType,
            @ApiParam(value = "Project ID (optional)")
            @RequestParam(required = false) Long projectId) {
        DashboardVO dashboard = dashboardService.getAssetTypeStats(assetType, projectId);
        return success(dashboard);
    }

    /**
     * Get quick summary (optimized for widgets)
     * Lighter version with essential stats only
     */
    @ApiOperation("Get quick dashboard summary")
    @PreAuthorize("@ss.hasPermi('asset:dashboard:query')")
    @GetMapping("/summary")
    public AjaxResult getQuickSummary(
            @ApiParam(value = "Project ID (optional)")
            @RequestParam(required = false) Long projectId) {
        // Returns management dashboard but could be further optimized
        DashboardVO dashboard = dashboardService.getManagementDashboard(projectId);
        return success(dashboard);
    }

    /**
     * Get house statistics
     */
    @ApiOperation("Get house statistics")
    @PreAuthorize("@ss.hasPermi('asset:house:query')")
    @GetMapping("/house")
    public AjaxResult getHouseStats(
            @ApiParam(value = "Project ID (optional)")
            @RequestParam(required = false) Long projectId) {
        DashboardVO dashboard = dashboardService.getAssetTypeStats("HOUSE", projectId);
        return success(dashboard.getHouseStatistics());
    }

    /**
     * Get parking statistics
     */
    @ApiOperation("Get parking statistics")
    @PreAuthorize("@ss.hasPermi('asset:parking:query')")
    @GetMapping("/parking")
    public AjaxResult getParkingStats(
            @ApiParam(value = "Project ID (optional)")
            @RequestParam(required = false) Long projectId) {
        DashboardVO dashboard = dashboardService.getAssetTypeStats("PARKING", projectId);
        return success(dashboard.getParkingStatistics());
    }

    /**
     * Get venue statistics
     */
    @ApiOperation("Get venue statistics")
    @PreAuthorize("@ss.hasPermi('asset:venue:query')")
    @GetMapping("/venue")
    public AjaxResult getVenueStats(
            @ApiParam(value = "Project ID (optional)")
            @RequestParam(required = false) Long projectId) {
        DashboardVO dashboard = dashboardService.getAssetTypeStats("VENUE", projectId);
        return success(dashboard.getVenueStatistics());
    }

    /**
     * Get office statistics
     */
    @ApiOperation("Get office statistics")
    @PreAuthorize("@ss.hasPermi('asset:office:query')")
    @GetMapping("/office")
    public AjaxResult getOfficeStats(
            @ApiParam(value = "Project ID (optional)")
            @RequestParam(required = false) Long projectId) {
        DashboardVO dashboard = dashboardService.getAssetTypeStats("OFFICE", projectId);
        return success(dashboard.getOfficeStatistics());
    }
}
