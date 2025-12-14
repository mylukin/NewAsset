package com.ruoyi.asset.service;

import com.ruoyi.asset.domain.dto.AssetHouseCreateDTO;
import com.ruoyi.asset.domain.dto.AssetHouseUpdateDTO;
import com.ruoyi.asset.domain.vo.AssetHouseDetailVO;
import com.ruoyi.asset.domain.vo.AssetHouseListVO;
import com.ruoyi.asset.domain.vo.HouseStatisticsVO;
import com.ruoyi.asset.mapper.AssetHouseMapper.AssetHouseQuery;

import java.util.List;

/**
 * House Asset Service Interface
 */
public interface IAssetHouseService {

    /**
     * Query house asset list with filters
     *
     * @param query query parameters
     * @return list of house assets
     */
    List<AssetHouseListVO> selectAssetHouseList(AssetHouseQuery query);

    /**
     * Query house asset detail by ID
     *
     * @param id asset ID
     * @return house asset detail
     */
    AssetHouseDetailVO selectAssetHouseById(Long id);

    /**
     * Create new house asset
     *
     * @param createDTO create data
     * @return created asset ID
     */
    Long insertAssetHouse(AssetHouseCreateDTO createDTO);

    /**
     * Update house asset
     *
     * @param updateDTO update data
     * @return affected rows
     */
    int updateAssetHouse(AssetHouseUpdateDTO updateDTO);

    /**
     * Delete house asset by ID
     *
     * @param id asset ID
     * @return affected rows
     */
    int deleteAssetHouseById(Long id);

    /**
     * Batch delete house assets
     *
     * @param ids asset IDs
     * @return affected rows
     */
    int deleteAssetHouseByIds(Long[] ids);

    /**
     * Change house asset status
     *
     * @param id        asset ID
     * @param newStatus new status code
     * @param reason    reason for change
     * @return affected rows
     */
    int changeStatus(Long id, String newStatus, String reason);

    /**
     * Get house asset statistics
     *
     * @param projectId optional project ID filter
     * @return statistics VO with calculated rates
     */
    HouseStatisticsVO getHouseStatistics(Long projectId);
}
