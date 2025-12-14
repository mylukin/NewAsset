package com.ruoyi.asset.service.impl;

import com.ruoyi.asset.domain.entity.AssetVenueUsage;
import com.ruoyi.asset.domain.vo.VenueUsageStatisticsVO;
import com.ruoyi.asset.mapper.AssetVenueUsageMapper;
import com.ruoyi.asset.mapper.AssetVenueUsageMapper.VenueUsageQuery;
import com.ruoyi.asset.service.IAssetVenueUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Venue Usage Service Implementation
 */
@Service
public class AssetVenueUsageServiceImpl implements IAssetVenueUsageService {

    @Autowired
    private AssetVenueUsageMapper venueUsageMapper;

    @Override
    public List<AssetVenueUsage> selectVenueUsageList(VenueUsageQuery query) {
        return venueUsageMapper.selectVenueUsageList(query);
    }

    @Override
    public AssetVenueUsage selectVenueUsageById(Long id) {
        return venueUsageMapper.selectVenueUsageById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertVenueUsage(AssetVenueUsage venueUsage) {
        venueUsageMapper.insertVenueUsage(venueUsage);
        return venueUsage.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateVenueUsage(AssetVenueUsage venueUsage) {
        return venueUsageMapper.updateVenueUsage(venueUsage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteVenueUsageById(Long id) {
        return venueUsageMapper.deleteVenueUsageById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteVenueUsageByIds(Long[] ids) {
        return venueUsageMapper.deleteVenueUsageByIds(ids);
    }

    @Override
    public VenueUsageStatisticsVO getUsageStatistics(Long venueId, Date startDate, Date endDate) {
        // Get overall statistics
        VenueUsageStatisticsVO statistics = venueUsageMapper.selectOverallStatistics(venueId, startDate, endDate);
        if (statistics == null) {
            statistics = new VenueUsageStatisticsVO();
            statistics.setTotalUsageCount(0);
            statistics.setTotalAttendance(0);
            statistics.setAvgAttendance(BigDecimal.ZERO);
        }

        // Get time slot statistics
        List<VenueUsageStatisticsVO.TimeSlotStatVO> timeSlotStats =
            venueUsageMapper.selectTimeSlotStatistics(venueId, startDate, endDate);

        // Calculate percentages for time slots
        if (timeSlotStats != null && !timeSlotStats.isEmpty() && statistics.getTotalUsageCount() > 0) {
            for (VenueUsageStatisticsVO.TimeSlotStatVO slot : timeSlotStats) {
                BigDecimal percentage = BigDecimal.valueOf(slot.getUsageCount())
                    .multiply(BigDecimal.valueOf(100))
                    .divide(BigDecimal.valueOf(statistics.getTotalUsageCount()), 2, RoundingMode.HALF_UP);
                slot.setPercentage(percentage);
            }
        }
        statistics.setPopularTimeSlots(timeSlotStats);

        // Get usage trend
        List<VenueUsageStatisticsVO.UsageTrendVO> trend =
            venueUsageMapper.selectUsageTrend(venueId, startDate, endDate);
        statistics.setUsageTrend(trend);

        // Get top organizers
        List<VenueUsageStatisticsVO.OrganizerStatVO> topOrganizers =
            venueUsageMapper.selectTopOrganizers(venueId, 10, startDate, endDate);
        statistics.setTopOrganizers(topOrganizers);

        // Calculate usage frequency (based on trend data)
        if (trend != null && !trend.isEmpty()) {
            int totalDays = trend.size();
            int totalUsage = statistics.getTotalUsageCount();

            if (totalDays > 0) {
                // Daily average
                statistics.setUsageFrequencyDaily(totalUsage / totalDays);
                // Weekly estimate (multiply by 7)
                statistics.setUsageFrequencyWeekly((totalUsage * 7) / totalDays);
                // Monthly estimate (multiply by 30)
                statistics.setUsageFrequencyMonthly((totalUsage * 30) / totalDays);
            }
        }

        // Build usage by type map
        VenueUsageQuery typeQuery = new VenueUsageQuery();
        typeQuery.setVenueId(venueId);
        typeQuery.setStartDate(startDate);
        typeQuery.setEndDate(endDate);
        List<AssetVenueUsage> allUsages = venueUsageMapper.selectVenueUsageList(typeQuery);

        Map<String, Integer> usageByType = new HashMap<>();
        if (allUsages != null) {
            for (AssetVenueUsage usage : allUsages) {
                if (usage.getUsageType() != null) {
                    usageByType.merge(usage.getUsageType(), 1, Integer::sum);
                }
            }
        }
        statistics.setUsageByType(usageByType);

        return statistics;
    }
}
