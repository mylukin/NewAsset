package com.ruoyi.asset.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.asset.domain.entity.AssetCodeSeq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Asset code sequence mapper
 */
@Mapper
public interface AssetCodeSeqMapper extends BaseMapper<AssetCodeSeq> {

    /**
     * Find sequence by rule, project, and year
     */
    @Select("SELECT * FROM t_asset_code_seq WHERE rule_id = #{ruleId} AND " +
            "(project_id = #{projectId} OR (#{projectId} IS NULL AND project_id IS NULL)) AND " +
            "(year = #{year} OR (#{year} IS NULL AND year IS NULL))")
    AssetCodeSeq findByRuleAndScope(@Param("ruleId") Long ruleId,
                                     @Param("projectId") Long projectId,
                                     @Param("year") Integer year);
}
