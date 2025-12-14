package com.ruoyi.asset.domain.parking;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Rental Information
 *
 * @author ruoyi
 */
public class RentalInfo {

    /** Rental start date */
    private LocalDate startDate;

    /** Rental end date */
    private LocalDate endDate;

    /** Monthly rent amount */
    private BigDecimal monthlyRent;

    /** Tenant name */
    private String tenantName;

    /** Tenant contact */
    private String tenantContact;

    /** Is active rental */
    private boolean active;

    public RentalInfo() {
    }

    public RentalInfo(LocalDate startDate, LocalDate endDate, BigDecimal monthlyRent) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.monthlyRent = monthlyRent;
        this.active = isWithinRentalPeriod();
    }

    public boolean isWithinRentalPeriod() {
        if (startDate == null || endDate == null) {
            return false;
        }
        LocalDate today = LocalDate.now();
        return !today.isBefore(startDate) && !today.isAfter(endDate);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(BigDecimal monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantContact() {
        return tenantContact;
    }

    public void setTenantContact(String tenantContact) {
        this.tenantContact = tenantContact;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
