package com.ruoyi.asset.mapper;

import com.ruoyi.asset.domain.entity.AssetMaintPlanAsset;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Maintenance Plan Asset Relation Mapper Interface
 */
@Mapper
public interface AssetMaintPlanAssetMapper {

    /**
     * Query asset IDs by plan ID
     *
     * @param planId plan ID
     * @return list of asset IDs
     */
    List<Long> selectAssetIdsByPlanId(@Param("planId") Long planId);

    /**
     * Batch insert plan-asset relations
     *
     * @param list list of relations
     * @return affected rows
     */
    int batchInsert(@Param("list") List<AssetMaintPlanAsset> list);

    /**
     * Delete relations by plan ID
     *
     * @param planId plan ID
     * @return affected rows
     */
    int deleteByPlanId(@Param("planId") Long planId);

    /**
     * Count assets in plan
     *
     * @param planId plan ID
     * @return count
     */
    int countByPlanId(@Param("planId") Long planId);
}
