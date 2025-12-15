package com.ruoyi.asset.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * AssetHouse Create DTO
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Data
public class AssetHouseCreateDTO {

    /**
     * Asset ID
     */
    @NotNull(message = "Asset ID is required")
    private Long assetId;

    /**
     * 建筑面积 (Building area in sqm)
     */
    private BigDecimal buildingArea;

    /**
     * 套内面积 (Inner usable area in sqm)
     */
    private BigDecimal innerArea;

    /**
     * 户型 (one_room, two_room, three_room, etc.)
     */
    private String houseType;

    /**
     * 房屋用途 (residential, commercial, apartment)
     */
    private String houseUsage;

    /**
     * 当前用途 (self_use, rented, idle)
     */
    private String currentUsage;

    /**
     * 当前使用人/承租人 (Current user/tenant)
     */
    private String currentUser;

    /**
     * 合同编号 (Contract number)
     */
    private String contractNo;

    /**
     * 租金单价 (Rent unit price)
     */
    private BigDecimal rentUnitPrice;

    /**
     * 租金总额 (Total rent)
     */
    private BigDecimal rentTotal;
}
