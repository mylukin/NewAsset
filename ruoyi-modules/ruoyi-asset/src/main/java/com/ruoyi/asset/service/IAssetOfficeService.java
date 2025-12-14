package com.ruoyi.asset.service;

import com.ruoyi.asset.domain.dto.AssetOfficeCreateDTO;
import com.ruoyi.asset.domain.dto.AssetOfficeUpdateDTO;
import com.ruoyi.asset.domain.vo.AssetOfficeDetailVO;
import com.ruoyi.asset.domain.vo.AssetOfficeListVO;
import com.ruoyi.asset.domain.vo.OfficeConditionStatisticsVO;
import com.ruoyi.asset.mapper.AssetOfficeMapper.AssetOfficeQuery;

import java.util.List;

/**
 * Office Asset Service Interface
 */
public interface IAssetOfficeService {

    /**
     * Query office asset list with filters
     *
     * @param query query parameters
     * @return list of office assets
     */
    List<AssetOfficeListVO> selectAssetOfficeList(AssetOfficeQuery query);

    /**
     * Query office asset detail by ID
     *
     * @param id asset ID
     * @return office asset detail
     */
    AssetOfficeDetailVO selectAssetOfficeById(Long id);

    /**
     * Create new office asset
     *
     * @param createDTO create data
     * @return created asset ID
     */
    Long insertAssetOffice(AssetOfficeCreateDTO createDTO);

    /**
     * Update office asset
     *
     * @param updateDTO update data
     * @return affected rows
     */
    int updateAssetOffice(AssetOfficeUpdateDTO updateDTO);

    /**
     * Delete office asset by ID
     *
     * @param id asset ID
     * @return affected rows
     */
    int deleteAssetOfficeById(Long id);

    /**
     * Batch delete office assets
     *
     * @param ids asset IDs
     * @return affected rows
     */
    int deleteAssetOfficeByIds(Long[] ids);

    /**
     * Change office asset status
     *
     * @param id        asset ID
     * @param newStatus new status code
     * @param reason    reason for change
     * @return affected rows
     */
    int changeStatus(Long id, String newStatus, String reason);

    /**
     * Get office condition statistics
     *
     * @param projectId optional project filter
     * @param deptId    optional department filter
     * @return condition statistics
     */
    OfficeConditionStatisticsVO getOfficeConditionStatistics(Long projectId, Long deptId);
}
