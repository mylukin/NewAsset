package com.ruoyi.asset.mapper;

import com.ruoyi.asset.domain.entity.AssetOffice;
import com.ruoyi.asset.domain.vo.AssetOfficeDetailVO;
import com.ruoyi.asset.domain.vo.AssetOfficeListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Office Asset Mapper Interface
 */
@Mapper
public interface AssetOfficeMapper {

    /**
     * Query office asset list with filters
     *
     * @param query query parameters
     * @return list of office assets
     */
    List<AssetOfficeListVO> selectAssetOfficeList(AssetOfficeQuery query);

    /**
     * Query office asset detail by ID
     *
     * @param id asset ID
     * @return office asset detail
     */
    AssetOfficeDetailVO selectAssetOfficeById(@Param("id") Long id);

    /**
     * Insert office asset extension
     *
     * @param assetOffice office asset entity
     * @return affected rows
     */
    int insertAssetOffice(AssetOffice assetOffice);

    /**
     * Update office asset extension
     *
     * @param assetOffice office asset entity
     * @return affected rows
     */
    int updateAssetOffice(AssetOffice assetOffice);

    /**
     * Delete office asset extension by ID
     *
     * @param id asset ID
     * @return affected rows
     */
    int deleteAssetOfficeById(@Param("id") Long id);

    /**
     * Batch delete office asset extensions
     *
     * @param ids asset IDs
     * @return affected rows
     */
    int deleteAssetOfficeByIds(@Param("ids") Long[] ids);

    /**
     * Query parameters class for office asset list
     */
    class AssetOfficeQuery {
        private Long projectId;
        private Long useDeptId;
        private Long dutyUserId;
        private String officeType;
        private String status;
        private String building;
        private String floor;
        private String assetName;
        private String assetCode;
        private String useUser;
        private String workplaceNo;
        private String serialNo;
        private String dataScope;

        public Long getProjectId() {
            return projectId;
        }

        public void setProjectId(Long projectId) {
            this.projectId = projectId;
        }

        public Long getUseDeptId() {
            return useDeptId;
        }

        public void setUseDeptId(Long useDeptId) {
            this.useDeptId = useDeptId;
        }

        public Long getDutyUserId() {
            return dutyUserId;
        }

        public void setDutyUserId(Long dutyUserId) {
            this.dutyUserId = dutyUserId;
        }

        public String getOfficeType() {
            return officeType;
        }

        public void setOfficeType(String officeType) {
            this.officeType = officeType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getUseUser() {
            return useUser;
        }

        public void setUseUser(String useUser) {
            this.useUser = useUser;
        }

        public String getWorkplaceNo() {
            return workplaceNo;
        }

        public void setWorkplaceNo(String workplaceNo) {
            this.workplaceNo = workplaceNo;
        }

        public String getSerialNo() {
            return serialNo;
        }

        public void setSerialNo(String serialNo) {
            this.serialNo = serialNo;
        }

        public String getDataScope() {
            return dataScope;
        }

        public void setDataScope(String dataScope) {
            this.dataScope = dataScope;
        }
    }
}
