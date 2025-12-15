package com.ruoyi.asset.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Asset Update DTO
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Data
public class AssetUpdateDTO {

    /**
     * Asset ID
     */
    @NotNull(message = "Asset ID is required")
    private Long id;

    /**
     * Asset name
     */
    private String name;

    /**
     * Asset status
     */
    private String status;

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
