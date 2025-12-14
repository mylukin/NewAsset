package com.ruoyi.asset.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;

import java.io.Serializable;

/**
 * Asset code sequence entity
 * Maps to t_asset_code_seq table
 */
@TableName("t_asset_code_seq")
public class AssetCodeSeq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Rule ID (FK)
     */
    private Long ruleId;

    /**
     * Project ID (for BY_PROJECT scope)
     */
    private Long projectId;

    /**
     * Year (for BY_PROJECT_YEAR scope)
     */
    private Integer year;

    /**
     * Current sequence number
     */
    private Long currentSeq = 0L;

    /**
     * Optimistic lock version
     */
    @Version
    private Integer version = 0;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Long getCurrentSeq() {
        return currentSeq;
    }

    public void setCurrentSeq(Long currentSeq) {
        this.currentSeq = currentSeq;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "AssetCodeSeq{" +
                "id=" + id +
                ", ruleId=" + ruleId +
                ", projectId=" + projectId +
                ", year=" + year +
                ", currentSeq=" + currentSeq +
                ", version=" + version +
                '}';
    }
}
