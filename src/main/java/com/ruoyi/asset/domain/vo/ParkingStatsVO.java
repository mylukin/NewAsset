package com.ruoyi.asset.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ParkingStatsVO - Statistics for parking assets
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Data
public class ParkingStatsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Total parking count
     */
    private Integer totalCount;

    /**
     * Self-use count
     */
    private Integer selfUseCount;

    /**
     * Rented count
     */
    private Integer rentedCount;

    /**
     * Idle count
     */
    private Integer idleCount;

    /**
     * Utilization rate (percentage)
     */
    private BigDecimal utilizationRate;
}
