package com.ruoyi.asset.domain.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Office asset condition statistics VO
 */
public class OfficeConditionStatisticsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Total asset count
     */
    private Integer totalCount;

    /**
     * Total original value
     */
    private BigDecimal totalOriginalValue;

    /**
     * Total net value
     */
    private BigDecimal totalNetValue;

    /**
     * Count by office type
     */
    private Map<String, Integer> countByOfficeType;

    /**
     * Count by status
     */
    private Map<String, Integer> countByStatus;

    /**
     * Department statistics for comparison
     */
    private List<DepartmentStatVO> departmentStats;

    /**
     * Assets with user assignments
     */
    private Integer assignedCount;

    /**
     * Assets without user assignments
     */
    private Integer unassignedCount;

    /**
     * IT equipment count (computers, servers)
     */
    private Integer itEquipmentCount;

    /**
     * Furniture count
     */
    private Integer furnitureCount;

    /**
     * Other office assets count
     */
    private Integer otherCount;

    // Getters and Setters

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getTotalOriginalValue() {
        return totalOriginalValue;
    }

    public void setTotalOriginalValue(BigDecimal totalOriginalValue) {
        this.totalOriginalValue = totalOriginalValue;
    }

    public BigDecimal getTotalNetValue() {
        return totalNetValue;
    }

    public void setTotalNetValue(BigDecimal totalNetValue) {
        this.totalNetValue = totalNetValue;
    }

    public Map<String, Integer> getCountByOfficeType() {
        return countByOfficeType;
    }

    public void setCountByOfficeType(Map<String, Integer> countByOfficeType) {
        this.countByOfficeType = countByOfficeType;
    }

    public Map<String, Integer> getCountByStatus() {
        return countByStatus;
    }

    public void setCountByStatus(Map<String, Integer> countByStatus) {
        this.countByStatus = countByStatus;
    }

    public List<DepartmentStatVO> getDepartmentStats() {
        return departmentStats;
    }

    public void setDepartmentStats(List<DepartmentStatVO> departmentStats) {
        this.departmentStats = departmentStats;
    }

    public Integer getAssignedCount() {
        return assignedCount;
    }

    public void setAssignedCount(Integer assignedCount) {
        this.assignedCount = assignedCount;
    }

    public Integer getUnassignedCount() {
        return unassignedCount;
    }

    public void setUnassignedCount(Integer unassignedCount) {
        this.unassignedCount = unassignedCount;
    }

    public Integer getItEquipmentCount() {
        return itEquipmentCount;
    }

    public void setItEquipmentCount(Integer itEquipmentCount) {
        this.itEquipmentCount = itEquipmentCount;
    }

    public Integer getFurnitureCount() {
        return furnitureCount;
    }

    public void setFurnitureCount(Integer furnitureCount) {
        this.furnitureCount = furnitureCount;
    }

    public Integer getOtherCount() {
        return otherCount;
    }

    public void setOtherCount(Integer otherCount) {
        this.otherCount = otherCount;
    }

    /**
     * Department statistics inner class
     */
    public static class DepartmentStatVO implements Serializable {
        private static final long serialVersionUID = 1L;

        private Long deptId;
        private String deptName;
        private Integer totalCount;
        private Integer itEquipmentCount;
        private Integer furnitureCount;
        private Integer otherCount;
        private BigDecimal totalValue;

        public Long getDeptId() {
            return deptId;
        }

        public void setDeptId(Long deptId) {
            this.deptId = deptId;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public Integer getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

        public Integer getItEquipmentCount() {
            return itEquipmentCount;
        }

        public void setItEquipmentCount(Integer itEquipmentCount) {
            this.itEquipmentCount = itEquipmentCount;
        }

        public Integer getFurnitureCount() {
            return furnitureCount;
        }

        public void setFurnitureCount(Integer furnitureCount) {
            this.furnitureCount = furnitureCount;
        }

        public Integer getOtherCount() {
            return otherCount;
        }

        public void setOtherCount(Integer otherCount) {
            this.otherCount = otherCount;
        }

        public BigDecimal getTotalValue() {
            return totalValue;
        }

        public void setTotalValue(BigDecimal totalValue) {
            this.totalValue = totalValue;
        }
    }
}
