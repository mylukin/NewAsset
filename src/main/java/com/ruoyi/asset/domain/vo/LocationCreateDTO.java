package com.ruoyi.asset.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Location Create DTO
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Data
public class LocationCreateDTO {

    /**
     * Parent location ID (0 for root)
     */
    private Long parentId;

    /**
     * Project ID
     */
    @NotNull(message = "Project ID is required")
    private Long projectId;

    /**
     * Hierarchy level (1=Building, 2=Floor, 3=Unit)
     */
    @NotNull(message = "Level is required")
    private Integer level;

    /**
     * Location code
     */
    @NotBlank(message = "Code is required")
    private String code;

    /**
     * Location name
     */
    @NotBlank(message = "Name is required")
    private String name;

    /**
     * Sort order
     */
    private Integer sortOrder;
}
