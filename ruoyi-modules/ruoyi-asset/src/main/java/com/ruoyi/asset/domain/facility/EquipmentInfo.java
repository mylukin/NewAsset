package com.ruoyi.asset.domain.facility;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Equipment Information
 *
 * @author ruoyi
 */
public class EquipmentInfo {

    /** Equipment ID */
    private String equipmentId;

    /** Equipment name */
    private String name;

    /** Equipment type (HVAC, ELEVATOR, FIRE_SYSTEM, etc.) */
    private String type;

    /** Model number */
    private String model;

    /** Manufacturer */
    private String manufacturer;

    /** Installation date */
    private LocalDate installationDate;

    /** Last maintenance date */
    private LocalDate lastMaintenanceDate;

    /** Next maintenance date */
    private LocalDate nextMaintenanceDate;

    /** Status (OPERATIONAL, MAINTENANCE, FAULTY, DECOMMISSIONED) */
    private String status;

    /** Purchase price */
    private BigDecimal purchasePrice;

    public EquipmentInfo() {
    }

    public EquipmentInfo(String equipmentId, String name, String type) {
        this.equipmentId = equipmentId;
        this.name = name;
        this.type = type;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public LocalDate getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(LocalDate installationDate) {
        this.installationDate = installationDate;
    }

    public LocalDate getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    public LocalDate getNextMaintenanceDate() {
        return nextMaintenanceDate;
    }

    public void setNextMaintenanceDate(LocalDate nextMaintenanceDate) {
        this.nextMaintenanceDate = nextMaintenanceDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
