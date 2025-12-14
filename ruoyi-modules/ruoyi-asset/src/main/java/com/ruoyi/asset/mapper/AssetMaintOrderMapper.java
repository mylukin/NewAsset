package com.ruoyi.asset.mapper;

import com.ruoyi.asset.domain.entity.AssetMaintOrder;
import com.ruoyi.asset.domain.vo.AssetMaintOrderDetailVO;
import com.ruoyi.asset.domain.vo.AssetMaintOrderListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Maintenance Order Mapper Interface
 */
@Mapper
public interface AssetMaintOrderMapper {

    /**
     * Query maintenance order list with filters
     *
     * @param query query parameters
     * @return list of maintenance orders
     */
    List<AssetMaintOrderListVO> selectMaintOrderList(MaintOrderQuery query);

    /**
     * Query maintenance order detail by ID
     *
     * @param id order ID
     * @return order detail
     */
    AssetMaintOrderDetailVO selectMaintOrderById(@Param("id") Long id);

    /**
     * Insert maintenance order
     *
     * @param order maintenance order entity
     * @return affected rows
     */
    int insertMaintOrder(AssetMaintOrder order);

    /**
     * Update maintenance order
     *
     * @param order maintenance order entity
     * @return affected rows
     */
    int updateMaintOrder(AssetMaintOrder order);

    /**
     * Delete maintenance order by ID
     *
     * @param id order ID
     * @return affected rows
     */
    int deleteMaintOrderById(@Param("id") Long id);

    /**
     * Batch delete maintenance orders
     *
     * @param ids order IDs
     * @return affected rows
     */
    int deleteMaintOrderByIds(@Param("ids") Long[] ids);

    /**
     * Check if order number exists
     *
     * @param orderNo order number
     * @return count
     */
    int checkOrderNoExists(@Param("orderNo") String orderNo);

    /**
     * Count orders by status
     *
     * @param projectId optional project filter
     * @param status    status code
     * @return count
     */
    int countByStatus(@Param("projectId") Long projectId, @Param("status") String status);

    /**
     * Query parameters class for maintenance order list
     */
    class MaintOrderQuery {
        private Long projectId;
        private Long assetId;
        private String assetType;
        private String orderType;
        private String faultType;
        private String status;
        private String priority;
        private Long currentHandlerId;
        private Long requesterId;
        private Date createTimeStart;
        private Date createTimeEnd;
        private String orderNo;
        private String title;
        private String dataScope;

        public Long getProjectId() {
            return projectId;
        }

        public void setProjectId(Long projectId) {
            this.projectId = projectId;
        }

        public Long getAssetId() {
            return assetId;
        }

        public void setAssetId(Long assetId) {
            this.assetId = assetId;
        }

        public String getAssetType() {
            return assetType;
        }

        public void setAssetType(String assetType) {
            this.assetType = assetType;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getFaultType() {
            return faultType;
        }

        public void setFaultType(String faultType) {
            this.faultType = faultType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPriority() {
            return priority;
        }

        public void setPriority(String priority) {
            this.priority = priority;
        }

        public Long getCurrentHandlerId() {
            return currentHandlerId;
        }

        public void setCurrentHandlerId(Long currentHandlerId) {
            this.currentHandlerId = currentHandlerId;
        }

        public Long getRequesterId() {
            return requesterId;
        }

        public void setRequesterId(Long requesterId) {
            this.requesterId = requesterId;
        }

        public Date getCreateTimeStart() {
            return createTimeStart;
        }

        public void setCreateTimeStart(Date createTimeStart) {
            this.createTimeStart = createTimeStart;
        }

        public Date getCreateTimeEnd() {
            return createTimeEnd;
        }

        public void setCreateTimeEnd(Date createTimeEnd) {
            this.createTimeEnd = createTimeEnd;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDataScope() {
            return dataScope;
        }

        public void setDataScope(String dataScope) {
            this.dataScope = dataScope;
        }
    }
}
