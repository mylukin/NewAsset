package com.ruoyi.asset.service;

import com.ruoyi.asset.domain.vo.DashboardVO;

/**
 * Dashboard Service Interface
 * Aggregates statistics from all asset modules for dashboard display
 */
public interface IDashboardService {

    /**
     * Get management dashboard data
     * Contains high-level KPIs for executives and managers
     *
     * @param projectId optional project filter
     * @return dashboard data with summary statistics
     */
    DashboardVO getManagementDashboard(Long projectId);

    /**
     * Get operations dashboard data
     * Contains operational metrics for operations staff
     * Focuses on maintenance orders, alerts, and daily operations
     *
     * @param projectId optional project filter
     * @return dashboard data with operational statistics
     */
    DashboardVO getOpsDashboard(Long projectId);

    /**
     * Get asset manager dashboard data
     * Contains portfolio data for asset managers
     * Focuses on assigned assets and their status
     *
     * @param projectId optional project filter
     * @return dashboard data with portfolio statistics
     */
    DashboardVO getAssetManagerDashboard(Long projectId);

    /**
     * Get quick stats for a specific asset type
     *
     * @param assetType asset type code (HOUSE, PARKING, VENUE, OFFICE, FACILITY)
     * @param projectId optional project filter
     * @return dashboard data filtered by asset type
     */
    DashboardVO getAssetTypeStats(String assetType, Long projectId);
}
