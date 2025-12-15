package com.ruoyi.asset.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ZoneStatsVO - Statistics for parking assets by zone
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Data
public class ZoneStatsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Zone name
     */
    private String zoneName;

    /**
     * Total parking count in zone
     */
    private Integer totalCount;

    /**
     * Used count in zone
     */
    private Integer usedCount;

    /**
     * Idle count in zone
     */
    private Integer idleCount;

    /**
     * Utilization rate (percentage)
     */
    private BigDecimal utilizationRate;
}
