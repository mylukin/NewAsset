package com.ruoyi.asset.mapper;

import com.ruoyi.asset.domain.entity.AssetHouse;
import com.ruoyi.asset.domain.vo.AssetHouseDetailVO;
import com.ruoyi.asset.domain.vo.AssetHouseListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * House Asset Mapper
 * Provides data access for house assets with joined base asset fields
 */
@Mapper
public interface AssetHouseMapper {

    /**
     * Query house asset list with filters
     * Supports data scope filtering via params.dataScope
     *
     * @param query query parameters including filters
     * @return list of house assets
     */
    List<AssetHouseListVO> selectAssetHouseList(AssetHouseQuery query);

    /**
     * Query house asset detail by ID
     * Includes all base asset fields and house extension fields
     *
     * @param id asset ID
     * @return house asset detail
     */
    AssetHouseDetailVO selectAssetHouseById(@Param("id") Long id);

    /**
     * Insert house asset extension record
     *
     * @param assetHouse house extension data
     * @return affected rows
     */
    int insertAssetHouse(AssetHouse assetHouse);

    /**
     * Update house asset extension record
     *
     * @param assetHouse house extension data
     * @return affected rows
     */
    int updateAssetHouse(AssetHouse assetHouse);

    /**
     * Delete house asset extension by ID
     *
     * @param id asset ID
     * @return affected rows
     */
    int deleteAssetHouseById(@Param("id") Long id);

    /**
     * Batch delete house asset extensions
     *
     * @param ids asset IDs
     * @return affected rows
     */
    int deleteAssetHouseByIds(@Param("ids") Long[] ids);

    /**
     * Query parameters for house asset list
     */
    class AssetHouseQuery {
        private Long projectId;
        private String building;
        private String floor;
        private String status;
        private String houseType;
        private String houseUsage;
        private String currentUsage;
        private BigDecimal minArea;
        private BigDecimal maxArea;
        private String assetName;
        private String assetCode;

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

        public String getHouseType() {
            return houseType;
        }

        public void setHouseType(String houseType) {
            this.houseType = houseType;
        }

        public String getHouseUsage() {
            return houseUsage;
        }

        public void setHouseUsage(String houseUsage) {
            this.houseUsage = houseUsage;
        }

        public String getCurrentUsage() {
            return currentUsage;
        }

        public void setCurrentUsage(String currentUsage) {
            this.currentUsage = currentUsage;
        }

        public BigDecimal getMinArea() {
            return minArea;
        }

        public void setMinArea(BigDecimal minArea) {
            this.minArea = minArea;
        }

        public BigDecimal getMaxArea() {
            return maxArea;
        }

        public void setMaxArea(BigDecimal maxArea) {
            this.maxArea = maxArea;
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

        public String getDataScope() {
            return dataScope;
        }

        public void setDataScope(String dataScope) {
            this.dataScope = dataScope;
        }
    }
}
