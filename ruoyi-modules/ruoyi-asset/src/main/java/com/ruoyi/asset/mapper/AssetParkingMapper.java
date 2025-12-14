package com.ruoyi.asset.mapper;

import com.ruoyi.asset.domain.entity.AssetParking;
import com.ruoyi.asset.domain.vo.AssetParkingDetailVO;
import com.ruoyi.asset.domain.vo.AssetParkingListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Parking Asset Mapper
 * Provides data access for parking assets with joined base asset fields
 */
@Mapper
public interface AssetParkingMapper {

    /**
     * Query parking asset list with filters
     * Supports data scope filtering via params.dataScope
     *
     * @param query query parameters including filters
     * @return list of parking assets
     */
    List<AssetParkingListVO> selectAssetParkingList(AssetParkingQuery query);

    /**
     * Query parking asset detail by ID
     * Includes all base asset fields and parking extension fields
     *
     * @param id asset ID
     * @return parking asset detail
     */
    AssetParkingDetailVO selectAssetParkingById(@Param("id") Long id);

    /**
     * Insert parking asset extension record
     *
     * @param assetParking parking extension data
     * @return affected rows
     */
    int insertAssetParking(AssetParking assetParking);

    /**
     * Update parking asset extension record
     *
     * @param assetParking parking extension data
     * @return affected rows
     */
    int updateAssetParking(AssetParking assetParking);

    /**
     * Delete parking asset extension by ID
     *
     * @param id asset ID
     * @return affected rows
     */
    int deleteAssetParkingById(@Param("id") Long id);

    /**
     * Batch delete parking asset extensions
     *
     * @param ids asset IDs
     * @return affected rows
     */
    int deleteAssetParkingByIds(@Param("ids") Long[] ids);

    /**
     * Query parameters for parking asset list
     */
    class AssetParkingQuery {
        private Long projectId;
        private String building;
        private String floor;
        private String status;
        private String parkingZone;
        private String parkingNo;
        private String parkingType;
        private String assetName;
        private String assetCode;
        private String currentUser;
        private String plateNo;

        /** Data scope SQL fragment - injected by DataScope aspect */
        private String dataScope;

        // Getters and Setters

        public Long getProjectId() {
            return projectId;
        }

        public void setProjectId(Long projectId) {
            this.projectId = projectId;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getParkingZone() {
            return parkingZone;
        }

        public void setParkingZone(String parkingZone) {
            this.parkingZone = parkingZone;
        }

        public String getParkingNo() {
            return parkingNo;
        }

        public void setParkingNo(String parkingNo) {
            this.parkingNo = parkingNo;
        }

        public String getParkingType() {
            return parkingType;
        }

        public void setParkingType(String parkingType) {
            this.parkingType = parkingType;
        }

        public String getAssetName() {
            return assetName;
        }

        public void setAssetName(String assetName) {
            this.assetName = assetName;
        }

        public String getAssetCode() {
            return assetCode;
        }

        public void setAssetCode(String assetCode) {
            this.assetCode = assetCode;
        }

        public String getCurrentUser() {
            return currentUser;
        }

        public void setCurrentUser(String currentUser) {
            this.currentUser = currentUser;
        }

        public String getPlateNo() {
            return plateNo;
        }

        public void setPlateNo(String plateNo) {
            this.plateNo = plateNo;
        }

        public String getDataScope() {
            return dataScope;
        }

        public void setDataScope(String dataScope) {
            this.dataScope = dataScope;
        }
    }
}
