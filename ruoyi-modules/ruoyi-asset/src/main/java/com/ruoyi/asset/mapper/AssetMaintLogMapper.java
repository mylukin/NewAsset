package com.ruoyi.asset.mapper;

import com.ruoyi.asset.domain.entity.AssetMaintLog;
import com.ruoyi.asset.domain.vo.AssetMaintLogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Maintenance Order Log Mapper Interface
 */
@Mapper
public interface AssetMaintLogMapper {

    /**
     * Query logs by order ID
     *
     * @param orderId order ID
     * @return list of logs
     */
    List<AssetMaintLogVO> selectLogsByOrderId(@Param("orderId") Long orderId);

    /**
     * Query log detail by ID
     *
     * @param id log ID
     * @return log detail
     */
    AssetMaintLogVO selectLogById(@Param("id") Long id);

    /**
     * Insert maintenance log
     *
     * @param log maintenance log entity
     * @return affected rows
     */
    int insertMaintLog(AssetMaintLog log);

    /**
     * Delete logs by order ID
     *
     * @param orderId order ID
     * @return affected rows
     */
    int deleteLogsByOrderId(@Param("orderId") Long orderId);

    /**
     * Get latest log for order
     *
     * @param orderId order ID
     * @return latest log
     */
    AssetMaintLogVO selectLatestLogByOrderId(@Param("orderId") Long orderId);
}
