package com.ruoyi.asset.domain.facility;

/**
 * Vendor Information
 *
 * @author ruoyi
 */
public class VendorInfo {

    /** Vendor ID */
    private String vendorId;

    /** Vendor name */
    private String name;

    /** Contact person */
    private String contactPerson;

    /** Contact phone */
    private String phone;

    /** Contact email */
    private String email;

    /** Address */
    private String address;

    /** Service type */
    private String serviceType;

    public VendorInfo() {
    }

    public VendorInfo(String vendorId, String name) {
        this.vendorId = vendorId;
        this.name = name;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
}
