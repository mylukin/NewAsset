package com.ruoyi.asset.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Maintenance Order Log entity
 * Maps to t_asset_maint_log table
 */
@TableName("t_asset_maint_log")
public class AssetMaintLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Order ID (FK)
     */
    @NotNull(message = "Order ID cannot be null")
    private Long orderId;

    /**
     * From status
     */
    @Size(max = 32, message = "From status must be at most 32 characters")
    private String fromStatus;

    /**
     * To status
     */
    @Size(max = 32, message = "To status must be at most 32 characters")
    private String toStatus;

    /**
     * Operator user ID
     */
    private Long operatorId;

    /**
     * Operation type: CREATE/ASSIGN/ACCEPT/START/COMPLETE/CONFIRM/REJECT/CANCEL
     */
    @NotBlank(message = "Operation type cannot be blank")
    @Size(max = 32, message = "Operation type must be at most 32 characters")
    private String operationType;

    /**
     * Operation content/remark
     */
    private String content;

    /**
     * Operation time
     */
    private Date opTime;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(String fromStatus) {
        this.fromStatus = fromStatus;
    }

    public String getToStatus() {
        return toStatus;
    }

    public void setToStatus(String toStatus) {
        this.toStatus = toStatus;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    @Override
    public String toString() {
        return "AssetMaintLog{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", operationType='" + operationType + '\'' +
                ", fromStatus='" + fromStatus + '\'' +
                ", toStatus='" + toStatus + '\'' +
                ", opTime=" + opTime +
                '}';
    }
}
