package com.ruoyi.asset.service;

import com.ruoyi.asset.domain.dto.AssetVenueCreateDTO;
import com.ruoyi.asset.domain.dto.AssetVenueUpdateDTO;
import com.ruoyi.asset.domain.vo.AssetVenueDetailVO;
import com.ruoyi.asset.domain.vo.AssetVenueListVO;
import com.ruoyi.asset.domain.vo.VenueStatisticsVO;
import com.ruoyi.asset.mapper.AssetVenueMapper.AssetVenueQuery;

import java.util.List;

/**
 * Venue Asset Service Interface
 */
public interface IAssetVenueService {

    /**
     * Query venue asset list with filters
     *
     * @param query query parameters
     * @return list of venue assets
     */
    List<AssetVenueListVO> selectAssetVenueList(AssetVenueQuery query);

    /**
     * Query venue asset detail by ID
     *
     * @param id asset ID
     * @return venue asset detail
     */
    AssetVenueDetailVO selectAssetVenueById(Long id);

    /**
     * Create new venue asset
     *
     * @param createDTO create data
     * @return created asset ID
     */
    Long insertAssetVenue(AssetVenueCreateDTO createDTO);

    /**
     * Update venue asset
     *
     * @param updateDTO update data
     * @return affected rows
     */
    int updateAssetVenue(AssetVenueUpdateDTO updateDTO);

    /**
     * Delete venue asset by ID
     *
     * @param id asset ID
     * @return affected rows
     */
    int deleteAssetVenueById(Long id);

    /**
     * Batch delete venue assets
     *
     * @param ids asset IDs
     * @return affected rows
     */
    int deleteAssetVenueByIds(Long[] ids);

    /**
     * Change venue asset status
     *
     * @param id        asset ID
     * @param newStatus new status code
     * @param reason    reason for change
     * @return affected rows
     */
    int changeStatus(Long id, String newStatus, String reason);

    /**
     * Get venue asset statistics
     *
     * @param projectId optional project filter
     * @param venueType optional venue type filter
     * @return venue statistics
     */
    VenueStatisticsVO getVenueStatistics(Long projectId, String venueType);
}
