package com.ruoyi.asset.domain.dashboard;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Dashboard Metrics DTO
 * Contains key metrics for the management dashboard
 *
 * @author ruoyi
 */
public class DashboardMetrics {

    /** Total number of assets */
    private Long totalAssets;

    /** Assets in use */
    private Long assetsInUse;

    /** Assets available */
    private Long assetsAvailable;

    /** Assets under maintenance */
    private Long assetsUnderMaintenance;

    /** Total asset value */
    private BigDecimal totalAssetValue;

    /** Monthly depreciation */
    private BigDecimal monthlyDepreciation;

    /** Pending work orders */
    private Long pendingWorkOrders;

    /** Completed work orders this month */
    private Long completedWorkOrdersThisMonth;

    /** Asset distribution by type */
    private Map<String, Long> assetsByType;

    /** Asset distribution by location */
    private Map<String, Long> assetsByLocation;

    /** Trend data for charts */
    private List<TrendDataPoint> assetTrend;

    /** Maintenance trend data */
    private List<TrendDataPoint> maintenanceTrend;

    public DashboardMetrics() {
    }

    public Long getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(Long totalAssets) {
        this.totalAssets = totalAssets;
    }

    public Long getAssetsInUse() {
        return assetsInUse;
    }

    public void setAssetsInUse(Long assetsInUse) {
        this.assetsInUse = assetsInUse;
    }

    public Long getAssetsAvailable() {
        return assetsAvailable;
    }

    public void setAssetsAvailable(Long assetsAvailable) {
        this.assetsAvailable = assetsAvailable;
    }

    public Long getAssetsUnderMaintenance() {
        return assetsUnderMaintenance;
    }

    public void setAssetsUnderMaintenance(Long assetsUnderMaintenance) {
        this.assetsUnderMaintenance = assetsUnderMaintenance;
    }

    public BigDecimal getTotalAssetValue() {
        return totalAssetValue;
    }

    public void setTotalAssetValue(BigDecimal totalAssetValue) {
        this.totalAssetValue = totalAssetValue;
    }

    public BigDecimal getMonthlyDepreciation() {
        return monthlyDepreciation;
    }

    public void setMonthlyDepreciation(BigDecimal monthlyDepreciation) {
        this.monthlyDepreciation = monthlyDepreciation;
    }

    public Long getPendingWorkOrders() {
        return pendingWorkOrders;
    }

    public void setPendingWorkOrders(Long pendingWorkOrders) {
        this.pendingWorkOrders = pendingWorkOrders;
    }

    public Long getCompletedWorkOrdersThisMonth() {
        return completedWorkOrdersThisMonth;
    }

    public void setCompletedWorkOrdersThisMonth(Long completedWorkOrdersThisMonth) {
        this.completedWorkOrdersThisMonth = completedWorkOrdersThisMonth;
    }

    public Map<String, Long> getAssetsByType() {
        return assetsByType;
    }

    public void setAssetsByType(Map<String, Long> assetsByType) {
        this.assetsByType = assetsByType;
    }

    public Map<String, Long> getAssetsByLocation() {
        return assetsByLocation;
    }

    public void setAssetsByLocation(Map<String, Long> assetsByLocation) {
        this.assetsByLocation = assetsByLocation;
    }

    public List<TrendDataPoint> getAssetTrend() {
        return assetTrend;
    }

    public void setAssetTrend(List<TrendDataPoint> assetTrend) {
        this.assetTrend = assetTrend;
    }

    public List<TrendDataPoint> getMaintenanceTrend() {
        return maintenanceTrend;
    }

    public void setMaintenanceTrend(List<TrendDataPoint> maintenanceTrend) {
        this.maintenanceTrend = maintenanceTrend;
    }
}
