package com.ruoyi.asset.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.asset.domain.entity.AssetCodeSeq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * AssetCodeSeqMapper interface
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Mapper
public interface AssetCodeSeqMapper extends BaseMapper<AssetCodeSeq> {

    /**
     * Update sequence with version check (optimistic locking)
     *
     * @param assetType asset type
     * @param newSeq new sequence number
     * @param expectedVersion expected version
     * @return number of rows updated
     */
    @Update("UPDATE t_asset_code_seq " +
            "SET current_seq = #{newSeq}, version = version + 1 " +
            "WHERE asset_type = #{assetType} AND version = #{expectedVersion}")
    int updateSeqWithVersion(@Param("assetType") String assetType,
                            @Param("newSeq") Long newSeq,
                            @Param("expectedVersion") Integer expectedVersion);
}
