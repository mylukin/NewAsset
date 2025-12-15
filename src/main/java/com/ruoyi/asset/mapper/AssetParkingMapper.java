package com.ruoyi.asset.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.asset.domain.entity.AssetParking;
import org.apache.ibatis.annotations.Mapper;

/**
 * AssetParkingMapper interface
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Mapper
public interface AssetParkingMapper extends BaseMapper<AssetParking> {

    /**
     * Select by asset ID
     *
     * @param assetId asset ID
     * @return AssetParking
     */
    AssetParking selectByAssetId(Long assetId);

    /**
     * Delete by asset ID
     *
     * @param assetId asset ID
     * @return result
     */
    int deleteByAssetId(Long assetId);
}
