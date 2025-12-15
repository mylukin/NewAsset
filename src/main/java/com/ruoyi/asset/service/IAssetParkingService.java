package com.ruoyi.asset.service;

import com.ruoyi.asset.domain.vo.AssetParkingCreateDTO;
import com.ruoyi.asset.domain.vo.AssetParkingUpdateDTO;
import com.ruoyi.asset.domain.vo.AssetParkingQueryDTO;
import com.ruoyi.asset.domain.vo.AssetParkingVO;
import com.ruoyi.asset.domain.vo.ParkingStatsVO;
import com.ruoyi.asset.domain.vo.ZoneStatsVO;
import com.ruoyi.common.core.page.PageResult;

import java.util.List;

/**
 * AssetParking Service Interface
 *
 * @author ruoyi
 * @date 2025-12-15
 */
public interface IAssetParkingService {

    /**
     * Create parking asset
     *
     * @param dto create DTO
     * @param operatorId operator user ID
     * @return created asset
     */
    AssetParkingVO create(AssetParkingCreateDTO dto, Long operatorId);

    /**
     * Update parking asset
     *
     * @param dto update DTO
     * @param operatorId operator user ID
     * @return updated asset
     */
    AssetParkingVO update(AssetParkingUpdateDTO dto, Long operatorId);

    /**
     * Delete parking assets (soft delete)
     *
     * @param ids asset IDs
     * @param operatorId operator user ID
     */
    void delete(Long[] ids, Long operatorId);

    /**
     * Get parking asset by ID
     *
     * @param id asset ID
     * @return asset
     */
    AssetParkingVO getById(Long id);

    /**
     * List parking assets with pagination
     *
     * @param query query DTO
     * @return page result
     */
    PageResult<AssetParkingVO> list(AssetParkingQueryDTO query);

    /**
     * Get parking statistics
     *
     * @param projectId project ID
     * @return parking statistics
     */
    ParkingStatsVO getStats(Long projectId);

    /**
     * Get parking statistics by zone
     *
     * @param projectId project ID
     * @return list of zone statistics
     */
    List<ZoneStatsVO> getStatsByZone(Long projectId);
}
