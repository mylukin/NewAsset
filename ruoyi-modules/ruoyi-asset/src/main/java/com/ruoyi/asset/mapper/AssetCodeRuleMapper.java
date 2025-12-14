package com.ruoyi.asset.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.asset.domain.entity.AssetCodeRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Asset code rule mapper
 */
@Mapper
public interface AssetCodeRuleMapper extends BaseMapper<AssetCodeRule> {

    /**
     * Find enabled rule by asset type
     */
    @Select("SELECT * FROM t_asset_code_rule WHERE asset_type = #{assetType} AND enabled = 1 LIMIT 1")
    AssetCodeRule findEnabledByAssetType(@Param("assetType") String assetType);

    /**
     * Find all enabled rules
     */
    @Select("SELECT * FROM t_asset_code_rule WHERE enabled = 1")
    List<AssetCodeRule> findAllEnabled();
}
