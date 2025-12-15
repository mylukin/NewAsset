package com.ruoyi.asset.service;

import com.ruoyi.asset.domain.entity.Asset;
import com.ruoyi.asset.domain.vo.AssetCreateDTO;
import com.ruoyi.asset.domain.vo.AssetUpdateDTO;
import com.ruoyi.asset.domain.vo.AssetQueryDTO;
import com.ruoyi.asset.domain.vo.AssetVO;
import com.ruoyi.common.core.page.PageResult;

/**
 * Asset Service Interface
 *
 * @author ruoyi
 * @date 2025-12-15
 */
public interface IAssetService {

    /**
     * Create asset
     *
     * @param dto create DTO
     * @param operatorId operator user ID
     * @return created asset
     */
    Asset create(AssetCreateDTO dto, Long operatorId);

    /**
     * Update asset
     *
     * @param dto update DTO
     * @param operatorId operator user ID
     * @return updated asset
     */
    Asset update(AssetUpdateDTO dto, Long operatorId);

    /**
     * Delete assets (soft delete)
     *
     * @param ids asset IDs
     * @param operatorId operator user ID
     */
    void delete(Long[] ids, Long operatorId);

    /**
     * Get asset by ID
     *
     * @param id asset ID
     * @return asset
     */
    Asset getById(Long id);

    /**
     * Get asset VO by ID
     *
     * @param id asset ID
     * @return asset VO
     */
    AssetVO getVOById(Long id);

    /**
     * List assets with pagination
     *
     * @param query query DTO
     * @return page result
     */
    PageResult<AssetVO> list(AssetQueryDTO query);
}
