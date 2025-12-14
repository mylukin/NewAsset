package com.ruoyi.asset.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Venue usage record entity
 * Maps to t_asset_venue_usage table
 */
@TableName("t_asset_venue_usage")
public class AssetVenueUsage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Usage record ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * Venue asset ID
     */
    @NotNull(message = "Venue ID is required")
    private Long venueId;

    /**
     * Usage date
     */
    @NotNull(message = "Usage date is required")
    private Date usageDate;

    /**
     * Start time
     */
    private Date startTime;

    /**
     * End time
     */
    private Date endTime;

    /**
     * Time slot (morning, afternoon, evening, full_day)
     */
    @Size(max = 32, message = "Time slot must be at most 32 characters")
    private String timeSlot;

    /**
     * Purpose/event name
     */
    @Size(max = 200, message = "Purpose must be at most 200 characters")
    private String purpose;

    /**
     * Organizer/department
     */
    @Size(max = 100, message = "Organizer must be at most 100 characters")
    private String organizer;

    /**
     * Actual attendance count
     */
    private Integer attendanceCount;

    /**
     * Booking reference number
     */
    @Size(max = 64, message = "Booking reference must be at most 64 characters")
    private String bookingRef;

    /**
     * Usage type (meeting, training, event, other)
     */
    @Size(max = 32, message = "Usage type must be at most 32 characters")
    private String usageType;

    /**
     * Status (scheduled, in_progress, completed, cancelled)
     */
    @Size(max = 32, message = "Status must be at most 32 characters")
    private String status;

    /**
     * Remark
     */
    @Size(max = 500, message = "Remark must be at most 500 characters")
    private String remark;

    /**
     * Create time
     */
    private Date createTime;

    /**
     * Update time
     */
    private Date updateTime;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public Date getUsageDate() {
        return usageDate;
    }

    public void setUsageDate(Date usageDate) {
        this.usageDate = usageDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public Integer getAttendanceCount() {
        return attendanceCount;
    }

    public void setAttendanceCount(Integer attendanceCount) {
        this.attendanceCount = attendanceCount;
    }

    public String getBookingRef() {
        return bookingRef;
    }

    public void setBookingRef(String bookingRef) {
        this.bookingRef = bookingRef;
    }

    public String getUsageType() {
        return usageType;
    }

    public void setUsageType(String usageType) {
        this.usageType = usageType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "AssetVenueUsage{" +
                "id=" + id +
                ", venueId=" + venueId +
                ", usageDate=" + usageDate +
                ", timeSlot='" + timeSlot + '\'' +
                ", purpose='" + purpose + '\'' +
                ", attendanceCount=" + attendanceCount +
                '}';
    }
}
