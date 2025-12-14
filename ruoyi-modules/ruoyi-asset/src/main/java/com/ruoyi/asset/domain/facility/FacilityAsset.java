package com.ruoyi.asset.domain.facility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Facility Asset Entity
 * Represents a facility asset with equipment and vendor information
 *
 * @author ruoyi
 */
public class FacilityAsset {

    public static final String MODULE = "facility";

    /** Primary key */
    private Long id;

    /** Facility ID */
    private String facilityId;

    /** Facility name */
    private String name;

    /** Facility type */
    private String type;

    /** Location */
    private String location;

    /** Building */
    private String building;

    /** Floor */
    private String floor;

    /** List of equipment in this facility */
    private List<EquipmentInfo> equipment = new ArrayList<>();

    /** List of vendors for this facility */
    private List<VendorInfo> vendors = new ArrayList<>();

    /** Extension fields for flexible data storage */
    private Map<String, Object> extensionFields = new HashMap<>();

    public FacilityAsset() {
    }

    public void addEquipment(EquipmentInfo equipmentInfo) {
        this.equipment.add(equipmentInfo);
    }

    public void addVendor(VendorInfo vendorInfo) {
        this.vendors.add(vendorInfo);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public List<EquipmentInfo> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<EquipmentInfo> equipment) {
        this.equipment = equipment;
    }

    public List<VendorInfo> getVendors() {
        return vendors;
    }

    public void setVendors(List<VendorInfo> vendors) {
        this.vendors = vendors;
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
