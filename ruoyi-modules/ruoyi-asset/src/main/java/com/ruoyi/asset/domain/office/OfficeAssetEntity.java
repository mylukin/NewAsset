package com.ruoyi.asset.domain.office;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Office Asset Entity
 * Represents office IT equipment assets like laptops, desktops, monitors, printers, etc.
 *
 * @author ruoyi
 */
public class OfficeAssetEntity {

    public static final String MODULE = "office";

    /** Primary key */
    private Long id;

    /** Asset tag/code for identification */
    private String assetTag;

    /** Asset name */
    private String name;

    /** Device type (LAPTOP, DESKTOP, MONITOR, PRINTER, etc.) */
    private String deviceType;

    /** Brand/Manufacturer */
    private String brand;

    /** Model number */
    private String model;

    /** Serial number */
    private String serialNumber;

    /** Purchase date */
    private LocalDate purchaseDate;

    /** Purchase price */
    private BigDecimal purchasePrice;

    /** Warranty expiry date */
    private LocalDate warrantyExpiry;

    /** Current status (IN_USE, AVAILABLE, MAINTENANCE, DISPOSED) */
    private String status;

    /** Assigned user/department */
    private String assignedTo;

    /** Location */
    private String location;

    /** Extension fields for flexible data storage */
    private Map<String, Object> extensionFields = new HashMap<>();

    public OfficeAssetEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public LocalDate getWarrantyExpiry() {
        return warrantyExpiry;
    }

    public void setWarrantyExpiry(LocalDate warrantyExpiry) {
        this.warrantyExpiry = warrantyExpiry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setExtensionField(String key, Object value) {
        this.extensionFields.put(key, value);
    }

    public Object getExtensionField(String key) {
        return this.extensionFields.get(key);
    }

    public Map<String, Object> getExtensionFields() {
        return extensionFields;
    }

    public void setExtensionFields(Map<String, Object> extensionFields) {
        this.extensionFields = extensionFields;
    }
}
