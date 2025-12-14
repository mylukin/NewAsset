package com.ruoyi.asset.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.asset.domain.entity.AssetStatusLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Asset status log mapper
 */
@Mapper
public interface AssetStatusLogMapper extends BaseMapper<AssetStatusLog> {

    /**
     * Find status logs by asset ID
     */
    @Select("SELECT * FROM t_asset_status_log WHERE asset_id = #{assetId} ORDER BY change_time DESC")
    List<AssetStatusLog> findByAssetId(@Param("assetId") Long assetId);

    /**
     * Find status logs by maintenance order ID
     */
    @Select("SELECT * FROM t_asset_status_log WHERE maint_order_id = #{maintOrderId} ORDER BY change_time DESC")
    List<AssetStatusLog> findByMaintOrderId(@Param("maintOrderId") Long maintOrderId);
}
