package com.ruoyi.asset.domain.vo;

import com.ruoyi.common.core.page.PageDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * AssetHouse Query DTO
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AssetHouseQueryDTO extends PageDomain {

    /**
     * Project ID
     */
    private Long projectId;

    /**
     * Asset type
     */
    private String assetType;

    /**
     * Status
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
     * Keyword (name or code)
     */
    private String keyword;

    /**
     * Start date for purchase date filter
     */
    private String purchaseDateStart;

    /**
     * End date for purchase date filter
     */
    private String purchaseDateEnd;

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
}
