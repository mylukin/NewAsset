package com.ruoyi.asset.service;

import com.ruoyi.asset.domain.vo.LocationInfo;

/**
 * Asset code generator service interface
 */
public interface AssetCodeGenerator {

    /**
     * Generate a unique asset code
     *
     * @param assetType    Asset type code (HOUSE, PARKING, etc.)
     * @param projectId    Project ID
     * @param locationInfo Location information (building, floor, etc.)
     * @return Generated unique asset code
     */
    String generate(String assetType, Long projectId, LocationInfo locationInfo);

    /**
     * Preview what the next code would be without incrementing the sequence
     *
     * @param assetType    Asset type code
     * @param projectId    Project ID
     * @param locationInfo Location information
     * @return Previewed asset code
     */
    String preview(String assetType, Long projectId, LocationInfo locationInfo);

    /**
     * Generate with retry on unique constraint violation
     *
     * @param assetType    Asset type code
     * @param projectId    Project ID
     * @param locationInfo Location information
     * @param maxRetries   Maximum retry attempts
     * @return Generated unique asset code
     */
    String generateWithRetry(String assetType, Long projectId, LocationInfo locationInfo, int maxRetries);
}
