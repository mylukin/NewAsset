package com.ruoyi.asset.service;

import com.ruoyi.asset.domain.vo.AssetHouseCreateDTO;
import com.ruoyi.asset.domain.vo.AssetHouseUpdateDTO;
import com.ruoyi.asset.domain.vo.AssetHouseQueryDTO;
import com.ruoyi.asset.domain.vo.AssetHouseVO;
import com.ruoyi.asset.domain.vo.HouseStatsVO;
import com.ruoyi.asset.domain.vo.UsageDistributionVO;
import com.ruoyi.common.core.page.PageResult;

import java.util.List;

/**
 * AssetHouse Service Interface
 *
 * @author ruoyi
 * @date 2025-12-15
 */
public interface IAssetHouseService {

    /**
     * Create house asset
     *
     * @param dto create DTO
     * @param operatorId operator user ID
     * @return created asset
     */
    AssetHouseVO create(AssetHouseCreateDTO dto, Long operatorId);

    /**
     * Update house asset
     *
     * @param dto update DTO
     * @param operatorId operator user ID
     * @return updated asset
     */
    AssetHouseVO update(AssetHouseUpdateDTO dto, Long operatorId);

    /**
     * Delete house assets (soft delete)
     *
     * @param ids asset IDs
     * @param operatorId operator user ID
     */
    void delete(Long[] ids, Long operatorId);

    /**
     * Get house asset by ID
     *
     * @param id asset ID
     * @return asset
     */
    AssetHouseVO getById(Long id);

    /**
     * List house assets with pagination
     *
     * @param query query DTO
     * @return page result
     */
    PageResult<AssetHouseVO> list(AssetHouseQueryDTO query);

    /**
     * Copy house asset for same-floor batch entry
     *
     * @param sourceId source asset ID
     * @param operatorId operator user ID
     * @return copied asset
     */
    AssetHouseVO copy(Long sourceId, Long operatorId);

    /**
     * Get house statistics for a project
     *
     * @param projectId project ID
     * @return statistics
     */
    HouseStatsVO getStats(Long projectId);

    /**
     * Get usage distribution for a project
     *
     * @param projectId project ID
     * @return usage distribution
     */
    List<UsageDistributionVO> getUsageDistribution(Long projectId);
}
