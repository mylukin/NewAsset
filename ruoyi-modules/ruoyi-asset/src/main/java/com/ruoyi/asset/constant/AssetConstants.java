package com.ruoyi.asset.constant;

/**
 * Asset module constants
 */
public class AssetConstants {

    /**
     * Asset type codes
     */
    public static final String ASSET_TYPE_HOUSE = "HOUSE";
    public static final String ASSET_TYPE_PARKING = "PARKING";
    public static final String ASSET_TYPE_FACILITY = "FACILITY";
    public static final String ASSET_TYPE_VENUE = "VENUE";
    public static final String ASSET_TYPE_OFFICE = "OFFICE";

    /**
     * Delete flag - not deleted
     */
    public static final String DEL_FLAG_NORMAL = "0";

    /**
     * Delete flag - deleted
     */
    public static final String DEL_FLAG_DELETED = "2";

    private AssetConstants() {
        // Utility class, prevent instantiation
    }
}
