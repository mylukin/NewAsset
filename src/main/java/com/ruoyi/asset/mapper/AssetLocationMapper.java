package com.ruoyi.asset.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.asset.domain.entity.AssetLocation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AssetLocationMapper interface
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Mapper
public interface AssetLocationMapper extends BaseMapper<AssetLocation> {

    /**
     * Select locations by project ID
     */
    List<AssetLocation> selectByProjectId(@Param("projectId") Long projectId);

    /**
     * Select children by parent ID
     */
    List<AssetLocation> selectChildrenByParentId(@Param("parentId") Long parentId);

    /**
     * Select locations with hierarchical structure
     */
    IPage<AssetLocation> selectLocationTree(Page<AssetLocation> page, @Param("projectId") Long projectId);
}
