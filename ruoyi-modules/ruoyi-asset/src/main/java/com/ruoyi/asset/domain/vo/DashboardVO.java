package com.ruoyi.asset.domain.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Dashboard data view object
 * Contains aggregated statistics from all asset modules
 */
public class DashboardVO implements Serializable {

    private static final long serialVersionUID = 1L;

    // ========== Summary Statistics ==========

    /**
     * Total asset count across all types
     */
    private Integer totalAssetCount;

    /**
     * Total asset value
     */
    private BigDecimal totalAssetValue;

    /**
     * Asset count by type (HOUSE, PARKING, VENUE, OFFICE, FACILITY)
     */
    private Map<String, Integer> assetCountByType;

    /**
     * Asset count by status (IN_USE, IDLE, MAINTAINING, etc.)
     */
    private Map<String, Integer> assetCountByStatus;

    // ========== House Statistics ==========

    private HouseStatisticsVO houseStatistics;

    // ========== Parking Statistics ==========

    private ParkingStatisticsVO parkingStatistics;

    // ========== Venue Statistics ==========

    private VenueStatisticsVO venueStatistics;

    // ========== Office Statistics ==========

    private OfficeConditionStatisticsVO officeStatistics;

    // ========== Maintenance Statistics ==========

    /**
     * Pending maintenance order count
     */
    private Integer pendingMaintOrderCount;

    /**
     * In-progress maintenance order count
     */
    private Integer processingMaintOrderCount;

    /**
     * Maintenance orders by status
     */
    private Map<String, Integer> maintOrderCountByStatus;

    /**
     * Recent maintenance orders (limit 10)
     */
    private List<AssetMaintOrderListVO> recentMaintOrders;

    // ========== Alerts and Notifications ==========

    /**
     * Overdue maintenance orders count
     */
    private Integer overdueMaintOrderCount;

    /**
     * Assets needing attention count
     */
    private Integer assetsNeedingAttentionCount;

    // Getters and Setters

    public Integer getTotalAssetCount() {
        return totalAssetCount;
    }

    public void setTotalAssetCount(Integer totalAssetCount) {
        this.totalAssetCount = totalAssetCount;
    }

    public BigDecimal getTotalAssetValue() {
        return totalAssetValue;
    }

    public void setTotalAssetValue(BigDecimal totalAssetValue) {
        this.totalAssetValue = totalAssetValue;
    }

    public Map<String, Integer> getAssetCountByType() {
        return assetCountByType;
    }

    public void setAssetCountByType(Map<String, Integer> assetCountByType) {
        this.assetCountByType = assetCountByType;
    }

    public Map<String, Integer> getAssetCountByStatus() {
        return assetCountByStatus;
    }

    public void setAssetCountByStatus(Map<String, Integer> assetCountByStatus) {
        this.assetCountByStatus = assetCountByStatus;
    }

    public HouseStatisticsVO getHouseStatistics() {
        return houseStatistics;
    }

    public void setHouseStatistics(HouseStatisticsVO houseStatistics) {
        this.houseStatistics = houseStatistics;
    }

    public ParkingStatisticsVO getParkingStatistics() {
        return parkingStatistics;
    }

    public void setParkingStatistics(ParkingStatisticsVO parkingStatistics) {
        this.parkingStatistics = parkingStatistics;
    }

    public VenueStatisticsVO getVenueStatistics() {
        return venueStatistics;
    }

    public void setVenueStatistics(VenueStatisticsVO venueStatistics) {
        this.venueStatistics = venueStatistics;
    }

    public OfficeConditionStatisticsVO getOfficeStatistics() {
        return officeStatistics;
    }

    public void setOfficeStatistics(OfficeConditionStatisticsVO officeStatistics) {
        this.officeStatistics = officeStatistics;
    }

    public Integer getPendingMaintOrderCount() {
        return pendingMaintOrderCount;
    }

    public void setPendingMaintOrderCount(Integer pendingMaintOrderCount) {
        this.pendingMaintOrderCount = pendingMaintOrderCount;
    }

    public Integer getProcessingMaintOrderCount() {
        return processingMaintOrderCount;
    }

    public void setProcessingMaintOrderCount(Integer processingMaintOrderCount) {
        this.processingMaintOrderCount = processingMaintOrderCount;
    }

    public Map<String, Integer> getMaintOrderCountByStatus() {
        return maintOrderCountByStatus;
    }

    public void setMaintOrderCountByStatus(Map<String, Integer> maintOrderCountByStatus) {
        this.maintOrderCountByStatus = maintOrderCountByStatus;
    }

    public List<AssetMaintOrderListVO> getRecentMaintOrders() {
        return recentMaintOrders;
    }

    public void setRecentMaintOrders(List<AssetMaintOrderListVO> recentMaintOrders) {
        this.recentMaintOrders = recentMaintOrders;
    }

    public Integer getOverdueMaintOrderCount() {
        return overdueMaintOrderCount;
    }

    public void setOverdueMaintOrderCount(Integer overdueMaintOrderCount) {
        this.overdueMaintOrderCount = overdueMaintOrderCount;
    }

    public Integer getAssetsNeedingAttentionCount() {
        return assetsNeedingAttentionCount;
    }

    public void setAssetsNeedingAttentionCount(Integer assetsNeedingAttentionCount) {
        this.assetsNeedingAttentionCount = assetsNeedingAttentionCount;
    }

    /**
     * Venue statistics inner class
     */
    public static class VenueStatisticsVO implements Serializable {
        private static final long serialVersionUID = 1L;

        private Integer totalCount;
        private Integer availableCount;
        private Integer occupiedCount;
        private Integer maintainingCount;
        private BigDecimal utilizationRate;
        private Integer todayBookingCount;

        public Integer getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

        public Integer getAvailableCount() {
            return availableCount;
        }

        public void setAvailableCount(Integer availableCount) {
            this.availableCount = availableCount;
        }

        public Integer getOccupiedCount() {
            return occupiedCount;
        }

        public void setOccupiedCount(Integer occupiedCount) {
            this.occupiedCount = occupiedCount;
        }

        public Integer getMaintainingCount() {
            return maintainingCount;
        }

        public void setMaintainingCount(Integer maintainingCount) {
            this.maintainingCount = maintainingCount;
        }

        public BigDecimal getUtilizationRate() {
            return utilizationRate;
        }

        public void setUtilizationRate(BigDecimal utilizationRate) {
            this.utilizationRate = utilizationRate;
        }

        public Integer getTodayBookingCount() {
            return todayBookingCount;
        }

        public void setTodayBookingCount(Integer todayBookingCount) {
            this.todayBookingCount = todayBookingCount;
        }
    }
}
