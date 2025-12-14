package com.ruoyi.asset.mapper;

import com.ruoyi.asset.domain.entity.AssetVenue;
import com.ruoyi.asset.domain.vo.AssetVenueDetailVO;
import com.ruoyi.asset.domain.vo.AssetVenueListVO;
import com.ruoyi.asset.domain.vo.VenueStatisticsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Venue Asset Mapper Interface
 */
@Mapper
public interface AssetVenueMapper {

    /**
     * Query venue asset list with filters
     *
     * @param query query parameters
     * @return list of venue assets
     */
    List<AssetVenueListVO> selectAssetVenueList(AssetVenueQuery query);

    /**
     * Query venue asset detail by ID
     *
     * @param id asset ID
     * @return venue asset detail
     */
    AssetVenueDetailVO selectAssetVenueById(@Param("id") Long id);

    /**
     * Insert venue asset extension
     *
     * @param assetVenue venue asset entity
     * @return affected rows
     */
    int insertAssetVenue(AssetVenue assetVenue);

    /**
     * Update venue asset extension
     *
     * @param assetVenue venue asset entity
     * @return affected rows
     */
    int updateAssetVenue(AssetVenue assetVenue);

    /**
     * Delete venue asset extension by ID
     *
     * @param id asset ID
     * @return affected rows
     */
    int deleteAssetVenueById(@Param("id") Long id);

    /**
     * Batch delete venue asset extensions
     *
     * @param ids asset IDs
     * @return affected rows
     */
    int deleteAssetVenueByIds(@Param("ids") Long[] ids);

    /**
     * Query venue statistics
     *
     * @param projectId   optional project filter
     * @param venueType   optional venue type filter
     * @return venue statistics
     */
    VenueStatisticsVO selectVenueStatistics(@Param("projectId") Long projectId, @Param("venueType") String venueType);

    /**
     * Query parameters class for venue asset list
     */
    class AssetVenueQuery {
        private Long projectId;
        private String building;
        private String floor;
        private String status;
        private String venueType;
        private String venueName;
        private String assetName;
        private String assetCode;
        private String bookable;
        private Integer minCapacity;
        private Integer maxCapacity;
        private String dataScope;

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

        public String getVenueType() {
            return venueType;
        }

        public void setVenueType(String venueType) {
            this.venueType = venueType;
        }

        public String getVenueName() {
            return venueName;
        }

        public void setVenueName(String venueName) {
            this.venueName = venueName;
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

        public String getBookable() {
            return bookable;
        }

        public void setBookable(String bookable) {
            this.bookable = bookable;
        }

        public Integer getMinCapacity() {
            return minCapacity;
        }

        public void setMinCapacity(Integer minCapacity) {
            this.minCapacity = minCapacity;
        }

        public Integer getMaxCapacity() {
            return maxCapacity;
        }

        public void setMaxCapacity(Integer maxCapacity) {
            this.maxCapacity = maxCapacity;
        }

        public String getDataScope() {
            return dataScope;
        }

        public void setDataScope(String dataScope) {
            this.dataScope = dataScope;
        }
    }
}
