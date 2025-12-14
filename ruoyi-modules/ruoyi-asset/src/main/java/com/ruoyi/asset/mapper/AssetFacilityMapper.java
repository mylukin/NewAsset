package com.ruoyi.asset.mapper;

import com.ruoyi.asset.domain.entity.AssetFacility;
import com.ruoyi.asset.domain.vo.AssetFacilityDetailVO;
import com.ruoyi.asset.domain.vo.AssetFacilityListVO;
import com.ruoyi.asset.domain.vo.FacilityWarrantyExpiringVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Facility Asset Mapper
 * Provides data access for facility assets with joined base asset fields
 */
@Mapper
public interface AssetFacilityMapper {

    /**
     * Query facility asset list with filters
     * Supports data scope filtering via params.dataScope
     *
     * @param query query parameters including filters
     * @return list of facility assets
     */
    List<AssetFacilityListVO> selectAssetFacilityList(AssetFacilityQuery query);

    /**
     * Query facility asset detail by ID
     * Includes all base asset fields and facility extension fields
     *
     * @param id asset ID
     * @return facility asset detail
     */
    AssetFacilityDetailVO selectAssetFacilityById(@Param("id") Long id);

    /**
     * Insert facility asset extension record
     *
     * @param assetFacility facility extension data
     * @return affected rows
     */
    int insertAssetFacility(AssetFacility assetFacility);

    /**
     * Update facility asset extension record
     *
     * @param assetFacility facility extension data
     * @return affected rows
     */
    int updateAssetFacility(AssetFacility assetFacility);

    /**
     * Delete facility asset extension by ID
     *
     * @param id asset ID
     * @return affected rows
     */
    int deleteAssetFacilityById(@Param("id") Long id);

    /**
     * Batch delete facility asset extensions
     *
     * @param ids asset IDs
     * @return affected rows
     */
    int deleteAssetFacilityByIds(@Param("ids") Long[] ids);

    /**
     * Query facilities with warranty expiring within specified days
     *
     * @param daysAhead number of days to look ahead
     * @param projectId optional project filter
     * @return list of facilities with expiring warranty
     */
    List<FacilityWarrantyExpiringVO> selectExpiringFacilities(@Param("daysAhead") int daysAhead, @Param("projectId") Long projectId);

    /**
     * Query parameters for facility asset list
     */
    class AssetFacilityQuery {
        private Long projectId;
        private String building;
        private String floor;
        private String status;
        private String facilityType;
        private String assetName;
        private String assetCode;
        private String equipmentNo;

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

        public String getFacilityType() {
            return facilityType;
        }

        public void setFacilityType(String facilityType) {
            this.facilityType = facilityType;
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

        public String getEquipmentNo() {
            return equipmentNo;
        }

        public void setEquipmentNo(String equipmentNo) {
            this.equipmentNo = equipmentNo;
        }

        public String getDataScope() {
            return dataScope;
        }

        public void setDataScope(String dataScope) {
            this.dataScope = dataScope;
        }
    }
}
