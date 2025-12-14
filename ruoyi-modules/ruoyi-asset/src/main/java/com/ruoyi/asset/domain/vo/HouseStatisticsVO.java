package com.ruoyi.asset.domain.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * House asset statistics view object
 */
public class HouseStatisticsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Total house count
     */
    private Integer totalCount;

    /**
     * Self-use house count
     */
    private Integer selfUseCount;

    /**
     * Rented house count
     */
    private Integer rentCount;

    /**
     * Idle house count
     */
    private Integer idleCount;

    /**
     * Total building area (sqm)
     */
    private BigDecimal totalBuildingArea;

    /**
     * Self-use area (sqm)
     */
    private BigDecimal selfUseArea;

    /**
     * Rented area (sqm)
     */
    private BigDecimal rentArea;

    /**
     * Idle area (sqm)
     */
    private BigDecimal idleArea;

    /**
     * Total rent income
     */
    private BigDecimal totalRentIncome;

    /**
     * Self-use rate (percentage)
     */
    private BigDecimal selfUseRate;

    /**
     * Rent rate (percentage)
     */
    private BigDecimal rentRate;

    /**
     * Idle rate (percentage)
     */
    private BigDecimal idleRate;

    /**
     * Count by status - in use
     */
    private Integer inUseCount;

    /**
     * Count by status - under maintenance
     */
    private Integer maintainingCount;

    /**
     * Count by status - pending disposal
     */
    private Integer pendingDisposalCount;

    // Calculate rates after setting counts
    public void calculateRates() {
        if (totalCount != null && totalCount > 0) {
            if (selfUseCount != null) {
                this.selfUseRate = BigDecimal.valueOf(selfUseCount)
                        .multiply(BigDecimal.valueOf(100))
                        .divide(BigDecimal.valueOf(totalCount), 2, RoundingMode.HALF_UP);
            }
            if (rentCount != null) {
                this.rentRate = BigDecimal.valueOf(rentCount)
                        .multiply(BigDecimal.valueOf(100))
                        .divide(BigDecimal.valueOf(totalCount), 2, RoundingMode.HALF_UP);
            }
            if (idleCount != null) {
                this.idleRate = BigDecimal.valueOf(idleCount)
                        .multiply(BigDecimal.valueOf(100))
                        .divide(BigDecimal.valueOf(totalCount), 2, RoundingMode.HALF_UP);
            }
        } else {
            this.selfUseRate = BigDecimal.ZERO;
            this.rentRate = BigDecimal.ZERO;
            this.idleRate = BigDecimal.ZERO;
        }
    }

    // Getters and Setters

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getSelfUseCount() {
        return selfUseCount;
    }

    public void setSelfUseCount(Integer selfUseCount) {
        this.selfUseCount = selfUseCount;
    }

    public Integer getRentCount() {
        return rentCount;
    }

    public void setRentCount(Integer rentCount) {
        this.rentCount = rentCount;
    }

    public Integer getIdleCount() {
        return idleCount;
    }

    public void setIdleCount(Integer idleCount) {
        this.idleCount = idleCount;
    }

    public BigDecimal getTotalBuildingArea() {
        return totalBuildingArea;
    }

    public void setTotalBuildingArea(BigDecimal totalBuildingArea) {
        this.totalBuildingArea = totalBuildingArea;
    }

    public BigDecimal getSelfUseArea() {
        return selfUseArea;
    }

    public void setSelfUseArea(BigDecimal selfUseArea) {
        this.selfUseArea = selfUseArea;
    }

    public BigDecimal getRentArea() {
        return rentArea;
    }

    public void setRentArea(BigDecimal rentArea) {
        this.rentArea = rentArea;
    }

    public BigDecimal getIdleArea() {
        return idleArea;
    }

    public void setIdleArea(BigDecimal idleArea) {
        this.idleArea = idleArea;
    }

    public BigDecimal getTotalRentIncome() {
        return totalRentIncome;
    }

    public void setTotalRentIncome(BigDecimal totalRentIncome) {
        this.totalRentIncome = totalRentIncome;
    }

    public BigDecimal getSelfUseRate() {
        return selfUseRate;
    }

    public void setSelfUseRate(BigDecimal selfUseRate) {
        this.selfUseRate = selfUseRate;
    }

    public BigDecimal getRentRate() {
        return rentRate;
    }

    public void setRentRate(BigDecimal rentRate) {
        this.rentRate = rentRate;
    }

    public BigDecimal getIdleRate() {
        return idleRate;
    }

    public void setIdleRate(BigDecimal idleRate) {
        this.idleRate = idleRate;
    }

    public Integer getInUseCount() {
        return inUseCount;
    }

    public void setInUseCount(Integer inUseCount) {
        this.inUseCount = inUseCount;
    }

    public Integer getMaintainingCount() {
        return maintainingCount;
    }

    public void setMaintainingCount(Integer maintainingCount) {
        this.maintainingCount = maintainingCount;
    }

    public Integer getPendingDisposalCount() {
        return pendingDisposalCount;
    }

    public void setPendingDisposalCount(Integer pendingDisposalCount) {
        this.pendingDisposalCount = pendingDisposalCount;
    }
}
