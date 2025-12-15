package com.ruoyi.asset.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * HouseStatsVO - Statistics for house assets
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Data
public class HouseStatsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Total house count
     */
    private Integer totalCount;

    /**
     * Self-use count
     */
    private Integer selfUseCount;

    /**
     * Rental count
     */
    private Integer rentalCount;

    /**
     * Idle count
     */
    private Integer idleCount;

    /**
     * Vacancy rate (percentage)
     */
    private BigDecimal vacancyRate;

    /**
     * Rental rate (percentage)
     */
    private BigDecimal rentalRate;
}
