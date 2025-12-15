package com.ruoyi.asset.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * UsageDistributionVO - Usage distribution statistics
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Data
public class UsageDistributionVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Usage type (self_use, rented, idle)
     */
    private String usageType;

    /**
     * Count of assets with this usage
     */
    private Integer count;

    /**
     * Percentage of total
     */
    private Double percentage;
}
