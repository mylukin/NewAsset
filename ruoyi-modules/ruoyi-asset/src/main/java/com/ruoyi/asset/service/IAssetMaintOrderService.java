package com.ruoyi.asset.service;

import com.ruoyi.asset.domain.dto.AssetMaintOrderCreateDTO;
import com.ruoyi.asset.domain.vo.AssetMaintOrderDetailVO;
import com.ruoyi.asset.domain.vo.AssetMaintOrderListVO;
import com.ruoyi.asset.mapper.AssetMaintOrderMapper.MaintOrderQuery;

import java.util.List;

/**
 * Maintenance Order Service Interface
 */
public interface IAssetMaintOrderService {

    /**
     * Query maintenance order list with filters
     *
     * @param query query parameters
     * @return list of maintenance orders
     */
    List<AssetMaintOrderListVO> selectMaintOrderList(MaintOrderQuery query);

    /**
     * Query maintenance order detail by ID (includes logs)
     *
     * @param id order ID
     * @return order detail with logs
     */
    AssetMaintOrderDetailVO selectMaintOrderById(Long id);

    /**
     * Create new maintenance order
     *
     * @param createDTO create data
     * @return created order ID
     */
    Long createMaintOrder(AssetMaintOrderCreateDTO createDTO);

    /**
     * Assign order to handler
     *
     * @param orderId   order ID
     * @param handlerId handler user ID
     * @param remark    assignment remark
     * @return affected rows
     */
    int assignOrder(Long orderId, Long handlerId, String remark);

    /**
     * Accept an assigned order
     *
     * @param orderId order ID
     * @param remark  acceptance remark
     * @return affected rows
     */
    int acceptOrder(Long orderId, String remark);

    /**
     * Reject an assigned order
     *
     * @param orderId order ID
     * @param reason  rejection reason
     * @return affected rows
     */
    int rejectOrder(Long orderId, String reason);

    /**
     * Complete maintenance work
     *
     * @param orderId order ID
     * @param result  completion result
     * @return affected rows
     */
    int completeOrder(Long orderId, String result);

    /**
     * Confirm and close order
     *
     * @param orderId  order ID
     * @param feedback feedback/comment
     * @return affected rows
     */
    int confirmAndClose(Long orderId, String feedback);

    /**
     * Cancel order
     *
     * @param orderId order ID
     * @param reason  cancellation reason
     * @return affected rows
     */
    int cancelOrder(Long orderId, String reason);

    /**
     * Delete maintenance order by ID
     *
     * @param id order ID
     * @return affected rows
     */
    int deleteMaintOrderById(Long id);

    /**
     * Batch delete maintenance orders
     *
     * @param ids order IDs
     * @return affected rows
     */
    int deleteMaintOrderByIds(Long[] ids);

    /**
     * Count orders by status
     *
     * @param projectId optional project filter
     * @param status    status code
     * @return count
     */
    int countByStatus(Long projectId, String status);

    /**
     * Get orders for a specific asset
     *
     * @param assetId asset ID
     * @return list of orders
     */
    List<AssetMaintOrderListVO> getOrdersByAssetId(Long assetId);
}
