package com.ruoyi.asset.service;

import com.ruoyi.asset.domain.dto.AssetFacilityCreateDTO;
import com.ruoyi.asset.domain.dto.AssetFacilityUpdateDTO;
import com.ruoyi.asset.domain.vo.AssetFacilityDetailVO;
import com.ruoyi.asset.domain.vo.AssetFacilityListVO;
import com.ruoyi.asset.mapper.AssetFacilityMapper.AssetFacilityQuery;

import java.util.List;

/**
 * Facility Asset Service Interface
 */
public interface IAssetFacilityService {

    /**
     * Query facility asset list with filters
     *
     * @param query query parameters
     * @return list of facility assets
     */
    List<AssetFacilityListVO> selectAssetFacilityList(AssetFacilityQuery query);

    /**
     * Query facility asset detail by ID
     *
     * @param id asset ID
     * @return facility asset detail
     */
    AssetFacilityDetailVO selectAssetFacilityById(Long id);

    /**
     * Create new facility asset
     *
     * @param createDTO create data
     * @return created asset ID
     */
    Long insertAssetFacility(AssetFacilityCreateDTO createDTO);

    /**
     * Update facility asset
     *
     * @param updateDTO update data
     * @return affected rows
     */
    int updateAssetFacility(AssetFacilityUpdateDTO updateDTO);

    /**
     * Delete facility asset by ID
     *
     * @param id asset ID
     * @return affected rows
     */
    int deleteAssetFacilityById(Long id);

    /**
     * Batch delete facility assets
     *
     * @param ids asset IDs
     * @return affected rows
     */
    int deleteAssetFacilityByIds(Long[] ids);

    /**
     * Change facility asset status
     *
     * @param id        asset ID
     * @param newStatus new status code
     * @param reason    reason for change
     * @return affected rows
     */
    int changeStatus(Long id, String newStatus, String reason);
}
