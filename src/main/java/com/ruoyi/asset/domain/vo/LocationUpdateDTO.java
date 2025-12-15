package com.ruoyi.asset.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Location Update DTO
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Data
public class LocationUpdateDTO {

    /**
     * Location ID
     */
    @NotNull(message = "ID is required")
    private Long id;

    /**
     * Location code
     */
    private String code;

    /**
     * Location name
     */
    private String name;

    /**
     * Sort order
     */
    private Integer sortOrder;

    /**
     * Status
     */
    private String status;
}
