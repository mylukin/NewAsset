package com.ruoyi.asset.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.asset.domain.entity.AssetAttachment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Asset attachment mapper
 */
@Mapper
public interface AssetAttachmentMapper extends BaseMapper<AssetAttachment> {

    /**
     * Find attachments by asset ID
     */
    @Select("SELECT * FROM t_asset_attachment WHERE asset_id = #{assetId} ORDER BY upload_time DESC")
    List<AssetAttachment> findByAssetId(@Param("assetId") Long assetId);

    /**
     * Delete attachments by asset ID
     */
    @Delete("DELETE FROM t_asset_attachment WHERE asset_id = #{assetId}")
    int deleteByAssetId(@Param("assetId") Long assetId);

    /**
     * Count attachments by asset ID
     */
    @Select("SELECT COUNT(*) FROM t_asset_attachment WHERE asset_id = #{assetId}")
    int countByAssetId(@Param("assetId") Long assetId);
}
