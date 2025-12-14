package com.ruoyi.asset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.asset.domain.entity.Asset;
import com.ruoyi.asset.domain.vo.*;
import com.ruoyi.asset.mapper.AssetMapper;
import com.ruoyi.asset.mapper.AssetMaintOrderMapper;
import com.ruoyi.asset.mapper.AssetMaintOrderMapper.MaintOrderQuery;
import com.ruoyi.asset.service.*;
import com.ruoyi.common.datascope.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Dashboard Service Implementation
 * Aggregates statistics from all asset modules
 */
@Service
public class DashboardServiceImpl implements IDashboardService {

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private AssetMaintOrderMapper maintOrderMapper;

    @Autowired
    private IAssetHouseService houseService;

    @Autowired
    private IAssetParkingService parkingService;

    @Autowired
    private IAssetVenueUsageService venueUsageService;

    @Autowired
    private IAssetOfficeService officeService;

    @Autowired
    private IAssetMaintOrderService maintOrderService;

    @Override
    @DataScope(projectAlias = "a", deptAlias = "a", userAlias = "a")
    public DashboardVO getManagementDashboard(Long projectId) {
        DashboardVO dashboard = new DashboardVO();

        // Get total asset statistics
        populateAssetSummary(dashboard, projectId);

        // Get house statistics
        dashboard.setHouseStatistics(houseService.getHouseStatistics(projectId));

        // Get parking statistics
        dashboard.setParkingStatistics(parkingService.getParkingStatistics(projectId, null));

        // Get office statistics
        dashboard.setOfficeStatistics(officeService.getOfficeConditionStatistics(projectId, null));

        // Get venue statistics
        populateVenueStatistics(dashboard, projectId);

        // Get maintenance summary
        populateMaintOrderSummary(dashboard, projectId);

        return dashboard;
    }

    @Override
    @DataScope(projectAlias = "a", deptAlias = "a", userAlias = "a")
    public DashboardVO getOpsDashboard(Long projectId) {
        DashboardVO dashboard = new DashboardVO();

        // Focus on operational metrics
        populateMaintOrderSummary(dashboard, projectId);

        // Get recent maintenance orders
        MaintOrderQuery query = new MaintOrderQuery();
        query.setProjectId(projectId);
        List<AssetMaintOrderListVO> orders = maintOrderMapper.selectMaintOrderList(query);
        if (orders != null && orders.size() > 10) {
            orders = orders.subList(0, 10);
        }
        dashboard.setRecentMaintOrders(orders);

        // Count overdue orders (would need a dedicated query, simplified here)
        dashboard.setOverdueMaintOrderCount(0);

        // Count assets needing attention
        dashboard.setAssetsNeedingAttentionCount(
                maintOrderService.countByStatus(projectId, "WAIT_ASSIGN") +
                maintOrderService.countByStatus(projectId, "WAIT_ACCEPT")
        );

        return dashboard;
    }

    @Override
    @DataScope(projectAlias = "a", deptAlias = "a", userAlias = "a")
    public DashboardVO getAssetManagerDashboard(Long projectId) {
        DashboardVO dashboard = new DashboardVO();

        // Get asset summary
        populateAssetSummary(dashboard, projectId);

        // Get house statistics
        dashboard.setHouseStatistics(houseService.getHouseStatistics(projectId));

        // Get parking statistics
        dashboard.setParkingStatistics(parkingService.getParkingStatistics(projectId, null));

        // Get maintenance summary for managed assets
        populateMaintOrderSummary(dashboard, projectId);

        return dashboard;
    }

    @Override
    public DashboardVO getAssetTypeStats(String assetType, Long projectId) {
        DashboardVO dashboard = new DashboardVO();

        switch (assetType) {
            case "HOUSE":
                dashboard.setHouseStatistics(houseService.getHouseStatistics(projectId));
                break;
            case "PARKING":
                dashboard.setParkingStatistics(parkingService.getParkingStatistics(projectId, null));
                break;
            case "OFFICE":
                dashboard.setOfficeStatistics(officeService.getOfficeConditionStatistics(projectId, null));
                break;
            case "VENUE":
                populateVenueStatistics(dashboard, projectId);
                break;
            default:
                // Return empty dashboard
                break;
        }

        return dashboard;
    }

    // ========== Private Helper Methods ==========

    private void populateAssetSummary(DashboardVO dashboard, Long projectId) {
        LambdaQueryWrapper<Asset> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Asset::getDelFlag, "0");
        if (projectId != null) {
            wrapper.eq(Asset::getProjectId, projectId);
        }

        List<Asset> assets = assetMapper.selectList(wrapper);

        // Total count
        dashboard.setTotalAssetCount(assets.size());

        // Total value
        BigDecimal totalValue = assets.stream()
                .map(a -> a.getOriginalValue() != null ? a.getOriginalValue() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        dashboard.setTotalAssetValue(totalValue);

        // Count by type
        Map<String, Integer> countByType = assets.stream()
                .collect(Collectors.groupingBy(
                        a -> a.getAssetType() != null ? a.getAssetType() : "UNKNOWN",
                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                ));
        dashboard.setAssetCountByType(countByType);

        // Count by status
        Map<String, Integer> countByStatus = assets.stream()
                .collect(Collectors.groupingBy(
                        a -> a.getStatus() != null ? a.getStatus() : "UNKNOWN",
                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                ));
        dashboard.setAssetCountByStatus(countByStatus);
    }

    private void populateMaintOrderSummary(DashboardVO dashboard, Long projectId) {
        // Pending orders (WAIT_ASSIGN + WAIT_ACCEPT)
        int waitAssign = maintOrderService.countByStatus(projectId, "WAIT_ASSIGN");
        int waitAccept = maintOrderService.countByStatus(projectId, "WAIT_ACCEPT");
        dashboard.setPendingMaintOrderCount(waitAssign + waitAccept);

        // Processing orders
        int processing = maintOrderService.countByStatus(projectId, "PROCESSING");
        dashboard.setProcessingMaintOrderCount(processing);

        // Count by status map
        Map<String, Integer> countByStatus = new HashMap<>();
        countByStatus.put("WAIT_ASSIGN", waitAssign);
        countByStatus.put("WAIT_ACCEPT", waitAccept);
        countByStatus.put("PROCESSING", processing);
        countByStatus.put("WAIT_CONFIRM", maintOrderService.countByStatus(projectId, "WAIT_CONFIRM"));
        countByStatus.put("CLOSED", maintOrderService.countByStatus(projectId, "CLOSED"));
        countByStatus.put("CANCELED", maintOrderService.countByStatus(projectId, "CANCELED"));
        dashboard.setMaintOrderCountByStatus(countByStatus);
    }

    private void populateVenueStatistics(DashboardVO dashboard, Long projectId) {
        DashboardVO.VenueStatisticsVO venueStats = new DashboardVO.VenueStatisticsVO();

        // Get overall venue usage statistics
        VenueUsageStatisticsVO usageStats = venueUsageService.getUsageStatistics(null, null, null);
        if (usageStats != null) {
            venueStats.setTotalCount(usageStats.getTotalBookings());
            venueStats.setUtilizationRate(usageStats.getAverageUtilization());
        }

        // Count venues by status from base asset table
        LambdaQueryWrapper<Asset> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Asset::getDelFlag, "0");
        wrapper.eq(Asset::getAssetType, "VENUE");
        if (projectId != null) {
            wrapper.eq(Asset::getProjectId, projectId);
        }

        List<Asset> venues = assetMapper.selectList(wrapper);
        venueStats.setTotalCount(venues.size());

        int available = 0, occupied = 0, maintaining = 0;
        for (Asset venue : venues) {
            String status = venue.getStatus();
            if ("IN_USE".equals(status)) {
                available++;
            } else if ("OCCUPIED".equals(status)) {
                occupied++;
            } else if ("MAINTAINING".equals(status)) {
                maintaining++;
            }
        }
        venueStats.setAvailableCount(available);
        venueStats.setOccupiedCount(occupied);
        venueStats.setMaintainingCount(maintaining);

        dashboard.setVenueStatistics(venueStats);
    }
}
