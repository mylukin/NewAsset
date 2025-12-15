package com.ruoyi.asset.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * AssetParking Update DTO
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Data
public class AssetParkingUpdateDTO {

    /**
     * Asset ID
     */
    @NotNull(message = "Asset ID is required")
    private Long assetId;

    /**
     * 车位编号 (Parking space number)
     */
    private String parkingNo;

    /**
     * 车库区域 (Parking zone)
     */
    private String parkingZone;

    /**
     * 车位类型 (fixed, temporary, reserved)
     */
    private String parkingType;

    /**
     * 车位面积 (Parking space area in sqm)
     */
    private BigDecimal area;

    /**
     * 当前使用人 (Current user)
     */
    private String currentUser;

    /**
     * 车牌号 (License plate number)
     */
    private String plateNo;

    /**
     * 租金/包月金额 (Rent price)
     */
    private BigDecimal rentPrice;

    /**
     * 合同编号 (Contract number)
     */
    private String contractNo;
}
