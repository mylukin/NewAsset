package com.ruoyi.asset.domain.vo;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * AssetVO - Value Object for view display
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Data
public class AssetVO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primary key
     */
    private Long id;

    /**
     * Asset code (unique, auto-generated)
     */
    private String assetCode;

    /**
     * Asset name
     */
    private String name;

    /**
     * Asset type (HOUSE, PARKING, FACILITY, VENUE, OFFICE)
     */
    private String assetType;

    /**
     * Asset status (9-state machine)
     */
    private String status;

    /**
     * Project ID
     */
    private Long projectId;

    /**
     * Building ID (level 1 location)
     */
    private Long buildingId;

    /**
     * Floor ID (level 2 location)
     */
    private Long floorId;

    /**
     * Unit ID (level 3 location)
     */
    private Long unitId;

    /**
     * Location description text
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
     * Purchase/acquisition date
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
     * Asset description
     */
    private String description;

    /**
     * Last status change time
     */
    private LocalDateTime statusChangedAt;

    /**
     * Last status change operator
     */
    private String statusChangedBy;

    /**
     * Delete flag (0=exists, 2=deleted)
     */
    private String delFlag;

    // Additional display fields can be added here
}
