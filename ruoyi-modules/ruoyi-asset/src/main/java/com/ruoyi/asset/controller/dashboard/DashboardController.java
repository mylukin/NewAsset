package com.ruoyi.asset.controller.dashboard;

import com.ruoyi.asset.domain.dashboard.DashboardMetrics;
import com.ruoyi.asset.domain.dashboard.TrendDataPoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Dashboard Controller
 * Provides dashboard metrics and chart data
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    /**
     * Get management dashboard metrics
     */
    @GetMapping("/management-home")
    public DashboardMetrics getManagementDashboard(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(required = false) String timezone) {

        DashboardMetrics metrics = new DashboardMetrics();

        // KPI data
        metrics.setTotalAssets(1250L);
        metrics.setAssetsInUse(980L);
        metrics.setAssetsAvailable(200L);
        metrics.setAssetsUnderMaintenance(70L);
        metrics.setTotalAssetValue(new BigDecimal("15680000.00"));
        metrics.setMonthlyDepreciation(new BigDecimal("125000.00"));
        metrics.setPendingWorkOrders(15L);
        metrics.setCompletedWorkOrdersThisMonth(45L);

        // Asset distribution by type
        Map<String, Long> assetsByType = new LinkedHashMap<>();
        assetsByType.put("House", 320L);
        assetsByType.put("Office", 450L);
        assetsByType.put("Parking", 280L);
        assetsByType.put("Facility", 200L);
        metrics.setAssetsByType(assetsByType);

        // Asset distribution by location
        Map<String, Long> assetsByLocation = new LinkedHashMap<>();
        assetsByLocation.put("Building A", 400L);
        assetsByLocation.put("Building B", 350L);
        assetsByLocation.put("Building C", 300L);
        assetsByLocation.put("Building D", 200L);
        metrics.setAssetsByLocation(assetsByLocation);

        // Trend data
        List<TrendDataPoint> assetTrend = generateTrendData(from, to);
        metrics.setAssetTrend(assetTrend);

        List<TrendDataPoint> maintenanceTrend = generateMaintenanceTrendData(from, to);
        metrics.setMaintenanceTrend(maintenanceTrend);

        return metrics;
    }

    private List<TrendDataPoint> generateTrendData(String from, String to) {
        List<TrendDataPoint> trend = new ArrayList<>();
        LocalDate startDate = from != null ? LocalDate.parse(from) : LocalDate.now().minusDays(30);
        LocalDate endDate = to != null ? LocalDate.parse(to) : LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        long baseValue = 1200L;
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            long value = baseValue + (long) (Math.random() * 50);
            trend.add(new TrendDataPoint(date.format(formatter), new BigDecimal(value)));
        }

        return trend;
    }

    private List<TrendDataPoint> generateMaintenanceTrendData(String from, String to) {
        List<TrendDataPoint> trend = new ArrayList<>();
        LocalDate startDate = from != null ? LocalDate.parse(from) : LocalDate.now().minusDays(30);
        LocalDate endDate = to != null ? LocalDate.parse(to) : LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            long value = (long) (Math.random() * 10);
            trend.add(new TrendDataPoint(date.format(formatter), new BigDecimal(value)));
        }

        return trend;
    }
}
