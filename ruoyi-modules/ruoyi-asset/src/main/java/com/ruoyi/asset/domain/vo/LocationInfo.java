package com.ruoyi.asset.domain.vo;

import java.io.Serializable;

/**
 * Location information for asset code generation
 */
public class LocationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Building/zone code
     */
    private String building;

    /**
     * Floor
     */
    private String floor;

    /**
     * Room number
     */
    private String roomNo;

    public LocationInfo() {
    }

    public LocationInfo(String building, String floor, String roomNo) {
        this.building = building;
        this.floor = floor;
        this.roomNo = roomNo;
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

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    @Override
    public String toString() {
        return "LocationInfo{" +
                "building='" + building + '\'' +
                ", floor='" + floor + '\'' +
                ", roomNo='" + roomNo + '\'' +
                '}';
    }
}
