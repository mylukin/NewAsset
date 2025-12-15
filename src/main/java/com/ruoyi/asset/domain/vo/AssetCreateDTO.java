package com.ruoyi.asset.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Asset Create DTO
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Data
public class AssetCreateDTO {

    /**
     * Asset name
     */
    @NotBlank(message = "Asset name is required")
    private String name;

    /**
     * Asset type (HOUSE, PARKING, FACILITY, VENUE, OFFICE)
     */
    @NotBlank(message = "Asset type is required")
    private String assetType;

    /**
     * Initial status
     */
    private String status;

    /**
     * Project ID
     */
    @NotNull(message = "Project ID is required")
    private Long projectId;

    /**
     * Building ID
     */
    private Long buildingId;

    /**
     * Floor ID
     */
    private Long floorId;

    /**
     * Unit ID
     */
    private Long unitId;

    /**
     * Location description
     */
    private String locationDesc;

    /**
     * Responsible person name
     */
    private String responsiblePerson;

    /**
     * Responsible person phone
     */
    private String responsiblePhone;

    /**
     * Purchase date
     */
    private LocalDate purchaseDate;

    /**
     * Purchase price
     */
    private BigDecimal purchasePrice;

    /**
     * Warranty expiration date
     */
    private LocalDate warrantyExpireDate;

    /**
     * Description
     */
    private String description;

    /**
     * Remark
     */
    private String remark;
}
