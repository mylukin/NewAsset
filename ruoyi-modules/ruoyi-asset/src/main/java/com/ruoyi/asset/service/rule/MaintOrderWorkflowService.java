package com.ruoyi.asset.service.rule;

import com.ruoyi.asset.domain.entity.AssetMaintOrder;

import java.util.Set;

/**
 * Maintenance Order Workflow Service Interface
 * Handles maintenance order status transitions and workflow logic
 */
public interface MaintOrderWorkflowService {

    // Order Status Constants
    String STATUS_WAIT_ASSIGN = "WAIT_ASSIGN";
    String STATUS_WAIT_ACCEPT = "WAIT_ACCEPT";
    String STATUS_PROCESSING = "PROCESSING";
    String STATUS_WAIT_CONFIRM = "WAIT_CONFIRM";
    String STATUS_CLOSED = "CLOSED";
    String STATUS_CANCELED = "CANCELED";

    // Operation Type Constants
    String OP_CREATE = "CREATE";
    String OP_ASSIGN = "ASSIGN";
    String OP_ACCEPT = "ACCEPT";
    String OP_REJECT = "REJECT";
    String OP_START = "START";
    String OP_COMPLETE = "COMPLETE";
    String OP_CONFIRM = "CONFIRM";
    String OP_CANCEL = "CANCEL";

    /**
     * Create a new maintenance order
     *
     * @param order      order entity with basic info filled
     * @param operatorId operator user ID
     * @return created order with ID
     */
    AssetMaintOrder createOrder(AssetMaintOrder order, Long operatorId);

    /**
     * Assign order to handler
     * Transition: WAIT_ASSIGN -> WAIT_ACCEPT
     *
     * @param orderId    order ID
     * @param handlerId  handler user ID
     * @param operatorId operator user ID
     * @param remark     assignment remark
     * @return updated order
     */
    AssetMaintOrder assignOrder(Long orderId, Long handlerId, Long operatorId, String remark);

    /**
     * Handler accepts the order
     * Transition: WAIT_ACCEPT -> PROCESSING
     *
     * @param orderId    order ID
     * @param operatorId operator (handler) user ID
     * @param remark     acceptance remark
     * @return updated order
     */
    AssetMaintOrder acceptOrder(Long orderId, Long operatorId, String remark);

    /**
     * Handler rejects the order
     * Transition: WAIT_ACCEPT -> WAIT_ASSIGN
     *
     * @param orderId    order ID
     * @param operatorId operator (handler) user ID
     * @param reason     rejection reason
     * @return updated order
     */
    AssetMaintOrder rejectOrder(Long orderId, Long operatorId, String reason);

    /**
     * Handler starts processing the order
     * Transition: PROCESSING (no status change, but logs start time)
     *
     * @param orderId    order ID
     * @param operatorId operator user ID
     * @param remark     start remark
     * @return updated order
     */
    AssetMaintOrder startProcessing(Long orderId, Long operatorId, String remark);

    /**
     * Handler completes the maintenance work
     * Transition: PROCESSING -> WAIT_CONFIRM
     *
     * @param orderId    order ID
     * @param operatorId operator user ID
     * @param result     completion result/summary
     * @return updated order
     */
    AssetMaintOrder completeOrder(Long orderId, Long operatorId, String result);

    /**
     * Requester confirms and closes the order
     * Transition: WAIT_CONFIRM -> CLOSED
     *
     * @param orderId    order ID
     * @param operatorId operator (requester) user ID
     * @param feedback   feedback/comment
     * @return updated order
     */
    AssetMaintOrder confirmAndClose(Long orderId, Long operatorId, String feedback);

    /**
     * Cancel an order
     * Transition: WAIT_ASSIGN/WAIT_ACCEPT -> CANCELED
     *
     * @param orderId    order ID
     * @param operatorId operator user ID
     * @param reason     cancellation reason
     * @return updated order
     */
    AssetMaintOrder cancelOrder(Long orderId, Long operatorId, String reason);

    /**
     * Check if a status transition is valid
     *
     * @param fromStatus current status
     * @param toStatus   target status
     * @return true if valid
     */
    boolean isValidTransition(String fromStatus, String toStatus);

    /**
     * Get valid next statuses for a given status
     *
     * @param currentStatus current status
     * @return set of valid next statuses
     */
    Set<String> getValidNextStatuses(String currentStatus);
}
