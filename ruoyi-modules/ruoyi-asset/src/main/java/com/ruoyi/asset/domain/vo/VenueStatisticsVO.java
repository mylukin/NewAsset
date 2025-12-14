package com.ruoyi.asset.domain.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Venue asset statistics view object
 */
public class VenueStatisticsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    // Count statistics
    private Integer totalCount;
    private Integer inUseCount;
    private Integer idleCount;
    private Integer maintainingCount;
    private Integer bookableCount;
    private Integer nonBookableCount;

    // Capacity statistics
    private Integer totalCapacity;
    private Integer avgCapacity;

    // Area statistics
    private BigDecimal totalArea;

    // Rate statistics
    private BigDecimal inUseRate;
    private BigDecimal idleRate;
    private BigDecimal bookableRate;

    // Type breakdown
    private Integer meetingRoomCount;
    private Integer conferenceHallCount;
    private Integer trainingRoomCount;
    private Integer multipurposeCount;
    private Integer otherCount;

    /**
     * Calculate rates based on counts
     */
    public void calculateRates() {
        if (totalCount != null && totalCount > 0) {
            if (inUseCount != null) {
                this.inUseRate = BigDecimal.valueOf(inUseCount)
                        .multiply(BigDecimal.valueOf(100))
                        .divide(BigDecimal.valueOf(totalCount), 2, RoundingMode.HALF_UP);
            }
            if (idleCount != null) {
                this.idleRate = BigDecimal.valueOf(idleCount)
                        .multiply(BigDecimal.valueOf(100))
                        .divide(BigDecimal.valueOf(totalCount), 2, RoundingMode.HALF_UP);
            }
            if (bookableCount != null) {
                this.bookableRate = BigDecimal.valueOf(bookableCount)
                        .multiply(BigDecimal.valueOf(100))
                        .divide(BigDecimal.valueOf(totalCount), 2, RoundingMode.HALF_UP);
            }
        }
    }

    // Getters and Setters

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getInUseCount() {
        return inUseCount;
    }

    public void setInUseCount(Integer inUseCount) {
        this.inUseCount = inUseCount;
    }

    public Integer getIdleCount() {
        return idleCount;
    }

    public void setIdleCount(Integer idleCount) {
        this.idleCount = idleCount;
    }

    public Integer getMaintainingCount() {
        return maintainingCount;
    }

    public void setMaintainingCount(Integer maintainingCount) {
        this.maintainingCount = maintainingCount;
    }

    public Integer getBookableCount() {
        return bookableCount;
    }

    public void setBookableCount(Integer bookableCount) {
        this.bookableCount = bookableCount;
    }

    public Integer getNonBookableCount() {
        return nonBookableCount;
    }

    public void setNonBookableCount(Integer nonBookableCount) {
        this.nonBookableCount = nonBookableCount;
    }

    public Integer getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(Integer totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public Integer getAvgCapacity() {
        return avgCapacity;
    }

    public void setAvgCapacity(Integer avgCapacity) {
        this.avgCapacity = avgCapacity;
    }

    public BigDecimal getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(BigDecimal totalArea) {
        this.totalArea = totalArea;
    }

    public BigDecimal getInUseRate() {
        return inUseRate;
    }

    public void setInUseRate(BigDecimal inUseRate) {
        this.inUseRate = inUseRate;
    }

    public BigDecimal getIdleRate() {
        return idleRate;
    }

    public void setIdleRate(BigDecimal idleRate) {
        this.idleRate = idleRate;
    }

    public BigDecimal getBookableRate() {
        return bookableRate;
    }

    public void setBookableRate(BigDecimal bookableRate) {
        this.bookableRate = bookableRate;
    }

    public Integer getMeetingRoomCount() {
        return meetingRoomCount;
    }

    public void setMeetingRoomCount(Integer meetingRoomCount) {
        this.meetingRoomCount = meetingRoomCount;
    }

    public Integer getConferenceHallCount() {
        return conferenceHallCount;
    }

    public void setConferenceHallCount(Integer conferenceHallCount) {
        this.conferenceHallCount = conferenceHallCount;
    }

    public Integer getTrainingRoomCount() {
        return trainingRoomCount;
    }

    public void setTrainingRoomCount(Integer trainingRoomCount) {
        this.trainingRoomCount = trainingRoomCount;
    }

    public Integer getMultipurposeCount() {
        return multipurposeCount;
    }

    public void setMultipurposeCount(Integer multipurposeCount) {
        this.multipurposeCount = multipurposeCount;
    }

    public Integer getOtherCount() {
        return otherCount;
    }

    public void setOtherCount(Integer otherCount) {
        this.otherCount = otherCount;
    }
}
