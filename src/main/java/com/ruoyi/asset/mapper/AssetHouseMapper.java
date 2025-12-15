package com.ruoyi.asset.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.asset.domain.entity.AssetHouse;
import org.apache.ibatis.annotations.Mapper;

/**
 * AssetHouseMapper interface
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Mapper
public interface AssetHouseMapper extends BaseMapper<AssetHouse> {

    /**
     * Select by asset ID
     *
     * @param assetId asset ID
     * @return AssetHouse
     */
    AssetHouse selectByAssetId(Long assetId);

    /**
     * Delete by asset ID
     *
     * @param assetId asset ID
     * @return result
     */
    int deleteByAssetId(Long assetId);
}
