package com.ruoyi.asset.service;

import com.ruoyi.asset.domain.dto.AssetParkingCreateDTO;
import com.ruoyi.asset.domain.dto.AssetParkingUpdateDTO;
import com.ruoyi.asset.domain.vo.AssetParkingDetailVO;
import com.ruoyi.asset.domain.vo.AssetParkingListVO;
import com.ruoyi.asset.mapper.AssetParkingMapper.AssetParkingQuery;

import java.util.List;

/**
 * Parking Asset Service Interface
 */
public interface IAssetParkingService {

    /**
     * Query parking asset list with filters
     *
     * @param query query parameters
     * @return list of parking assets
     */
    List<AssetParkingListVO> selectAssetParkingList(AssetParkingQuery query);

    /**
     * Query parking asset detail by ID
     *
     * @param id asset ID
     * @return parking asset detail
     */
    AssetParkingDetailVO selectAssetParkingById(Long id);

    /**
     * Create new parking asset
     *
     * @param createDTO create data
     * @return created asset ID
     */
    Long insertAssetParking(AssetParkingCreateDTO createDTO);

    /**
     * Update parking asset
     *
     * @param updateDTO update data
     * @return affected rows
     */
    int updateAssetParking(AssetParkingUpdateDTO updateDTO);

    /**
     * Delete parking asset by ID
     *
     * @param id asset ID
     * @return affected rows
     */
    int deleteAssetParkingById(Long id);

    /**
     * Batch delete parking assets
     *
     * @param ids asset IDs
     * @return affected rows
     */
    int deleteAssetParkingByIds(Long[] ids);

    /**
     * Change parking asset status
     *
     * @param id        asset ID
     * @param newStatus new status code
     * @param reason    reason for change
     * @return affected rows
     */
    int changeStatus(Long id, String newStatus, String reason);
}
