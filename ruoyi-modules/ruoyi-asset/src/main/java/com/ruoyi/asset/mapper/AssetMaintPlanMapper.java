package com.ruoyi.asset.mapper;

import com.ruoyi.asset.domain.entity.AssetMaintPlan;
import com.ruoyi.asset.domain.vo.AssetMaintPlanVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Maintenance Plan Mapper Interface
 */
@Mapper
public interface AssetMaintPlanMapper {

    /**
     * Query maintenance plan list with filters
     *
     * @param query query parameters
     * @return list of maintenance plans
     */
    List<AssetMaintPlanVO> selectMaintPlanList(MaintPlanQuery query);

    /**
     * Query maintenance plan detail by ID
     *
     * @param id plan ID
     * @return plan detail
     */
    AssetMaintPlanVO selectMaintPlanById(@Param("id") Long id);

    /**
     * Insert maintenance plan
     *
     * @param plan maintenance plan entity
     * @return affected rows
     */
    int insertMaintPlan(AssetMaintPlan plan);

    /**
     * Update maintenance plan
     *
     * @param plan maintenance plan entity
     * @return affected rows
     */
    int updateMaintPlan(AssetMaintPlan plan);

    /**
     * Delete maintenance plan by ID
     *
     * @param id plan ID
     * @return affected rows
     */
    int deleteMaintPlanById(@Param("id") Long id);

    /**
     * Batch delete maintenance plans
     *
     * @param ids plan IDs
     * @return affected rows
     */
    int deleteMaintPlanByIds(@Param("ids") Long[] ids);

    /**
     * Select enabled plans that need to generate orders
     *
     * @param currentTime current time for comparison
     * @return list of plans ready for generation
     */
    List<AssetMaintPlan> selectPlansForGeneration(@Param("currentTime") Date currentTime);

    /**
     * Update next generate time
     *
     * @param id plan ID
     * @param nextTime next generation time
     * @return affected rows
     */
    int updateNextGenerateTime(@Param("id") Long id, @Param("nextTime") Date nextTime);

    /**
     * Query parameters class for maintenance plan list
     */
    class MaintPlanQuery {
        private Long projectId;
        private String assetType;
        private String assetCategory;
        private String cycleType;
        private String status;
        private String planName;
        private String dataScope;

        public Long getProjectId() {
            return projectId;
        }

        public void setProjectId(Long projectId) {
            this.projectId = projectId;
        }

        public String getAssetType() {
            return assetType;
        }

        public void setAssetType(String assetType) {
            this.assetType = assetType;
        }

        public String getAssetCategory() {
            return assetCategory;
        }

        public void setAssetCategory(String assetCategory) {
            this.assetCategory = assetCategory;
        }

        public String getCycleType() {
            return cycleType;
        }

        public void setCycleType(String cycleType) {
            this.cycleType = cycleType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPlanName() {
            return planName;
        }

        public void setPlanName(String planName) {
            this.planName = planName;
        }

        public String getDataScope() {
            return dataScope;
        }

        public void setDataScope(String dataScope) {
            this.dataScope = dataScope;
        }
    }
}
