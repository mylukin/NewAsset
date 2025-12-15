package com.ruoyi.asset.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.asset.domain.entity.AssetAttachment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AssetAttachmentMapper interface
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Mapper
public interface AssetAttachmentMapper extends BaseMapper<AssetAttachment> {

    /**
     * Select attachments by asset ID
     */
    List<AssetAttachment> selectByAssetId(Long assetId);
}
