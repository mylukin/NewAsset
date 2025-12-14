package com.ruoyi.asset.domain.house;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * House Asset Entity
 * Represents a house/property asset with extension fields for area, type, and rental info
 *
 * @author ruoyi
 */
public class HouseAssetEntity {

    public static final String MODULE = "house";

    /** Primary key */
    private Long id;

    /** Asset code */
    private String assetCode;

    /** Asset name */
    private String name;

    /** House area in square meters */
    private BigDecimal area;

    /** House type (apartment, villa, commercial, etc.) */
    private String type;

    /** Is for rent */
    private Boolean isForRent;

    /** Monthly rent amount */
    private BigDecimal monthlyRent;

    /** Rental deposit */
    private BigDecimal rentalDeposit;

    /** Extension fields for flexible data storage */
    private Map<String, Object> extensionFields = new HashMap<>();

    public HouseAssetEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsForRent() {
        return isForRent;
    }

    public void setIsForRent(Boolean isForRent) {
        this.isForRent = isForRent;
    }

    public BigDecimal getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(BigDecimal monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public BigDecimal getRentalDeposit() {
        return rentalDeposit;
    }

    public void setRentalDeposit(BigDecimal rentalDeposit) {
        this.rentalDeposit = rentalDeposit;
    }

    /**
     * Set an extension field value
     *
     * @param key   field name
     * @param value field value
     */
    public void setExtensionField(String key, Object value) {
        this.extensionFields.put(key, value);
    }

    /**
     * Get an extension field value
     *
     * @param key field name
     * @return field value or null if not found
     */
    public Object getExtensionField(String key) {
        return this.extensionFields.get(key);
    }

    /**
     * Get all extension fields
     *
     * @return map of all extension fields
     */
    public Map<String, Object> getExtensionFields() {
        return extensionFields;
    }

    public void setExtensionFields(Map<String, Object> extensionFields) {
        this.extensionFields = extensionFields;
    }
}
