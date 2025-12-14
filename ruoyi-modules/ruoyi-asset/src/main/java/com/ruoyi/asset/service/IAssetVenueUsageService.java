package com.ruoyi.asset.service;

import com.ruoyi.asset.domain.entity.AssetVenueUsage;
import com.ruoyi.asset.domain.vo.VenueUsageStatisticsVO;
import com.ruoyi.asset.mapper.AssetVenueUsageMapper.VenueUsageQuery;

import java.util.Date;
import java.util.List;

/**
 * Venue Usage Service Interface
 */
public interface IAssetVenueUsageService {

    /**
     * Query venue usage list
     *
     * @param query query parameters
     * @return list of venue usage records
     */
    List<AssetVenueUsage> selectVenueUsageList(VenueUsageQuery query);

    /**
     * Query venue usage by ID
     *
     * @param id usage record ID
     * @return venue usage record
     */
    AssetVenueUsage selectVenueUsageById(Long id);

    /**
     * Insert venue usage record
     *
     * @param venueUsage venue usage entity
     * @return inserted record ID
     */
    Long insertVenueUsage(AssetVenueUsage venueUsage);

    /**
     * Update venue usage record
     *
     * @param venueUsage venue usage entity
     * @return affected rows
     */
    int updateVenueUsage(AssetVenueUsage venueUsage);

    /**
     * Delete venue usage by ID
     *
     * @param id usage record ID
     * @return affected rows
     */
    int deleteVenueUsageById(Long id);

    /**
     * Batch delete venue usage records
     *
     * @param ids usage record IDs
     * @return affected rows
     */
    int deleteVenueUsageByIds(Long[] ids);

    /**
     * Get complete usage statistics for a venue
     *
     * @param venueId   optional venue filter
     * @param startDate optional start date
     * @param endDate   optional end date
     * @return complete usage statistics
     */
    VenueUsageStatisticsVO getUsageStatistics(Long venueId, Date startDate, Date endDate);
}
