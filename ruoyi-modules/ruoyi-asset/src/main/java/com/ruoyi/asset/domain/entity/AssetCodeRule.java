package com.ruoyi.asset.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Asset code rule entity
 * Maps to t_asset_code_rule table
 */
@TableName("t_asset_code_rule")
public class AssetCodeRule extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Asset type
     */
    @NotBlank(message = "Asset type cannot be blank")
    @Size(max = 32, message = "Asset type must be at most 32 characters")
    private String assetType;

    /**
     * Rule name
     */
    @NotBlank(message = "Rule name cannot be blank")
    @Size(max = 100, message = "Rule name must be at most 100 characters")
    private String ruleName;

    /**
     * Code pattern template
     * Supports placeholders: {PROJECT}, {TYPE}, {BUILDING}, {FLOOR}, {SEQ}
     */
    @NotBlank(message = "Pattern cannot be blank")
    @Size(max = 200, message = "Pattern must be at most 200 characters")
    private String pattern;

    /**
     * Separator character
     */
    @Size(max = 8, message = "Separator must be at most 8 characters")
    private String separator = "-";

    /**
     * Sequence number length (with zero-padding)
     */
    private Integer seqLength = 4;

    /**
     * Reset scope: NONE/BY_PROJECT/BY_PROJECT_YEAR
     */
    @Size(max = 32, message = "Reset scope must be at most 32 characters")
    private String resetScope = "NONE";

    /**
     * Enabled flag: 1=yes, 0=no
     */
    private Boolean enabled = true;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public Integer getSeqLength() {
        return seqLength;
    }

    public void setSeqLength(Integer seqLength) {
        this.seqLength = seqLength;
    }

    public String getResetScope() {
        return resetScope;
    }

    public void setResetScope(String resetScope) {
        this.resetScope = resetScope;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "AssetCodeRule{" +
                "id=" + id +
                ", assetType='" + assetType + '\'' +
                ", ruleName='" + ruleName + '\'' +
                ", pattern='" + pattern + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
