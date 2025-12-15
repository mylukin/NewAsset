package com.ruoyi.asset.domain.vo;

import com.ruoyi.common.core.page.PageDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * AssetParking Query DTO
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AssetParkingQueryDTO extends PageDomain {

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
     * Parking zone
     */
    private String parkingZone;

    /**
     * Parking type
     */
    private String parkingType;
}
