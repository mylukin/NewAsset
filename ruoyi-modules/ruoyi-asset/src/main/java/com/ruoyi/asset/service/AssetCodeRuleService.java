package com.ruoyi.asset.service;

import com.ruoyi.asset.domain.entity.AssetCodeRule;

import java.util.List;

/**
 * Asset code rule service interface
 */
public interface AssetCodeRuleService {

    /**
     * Get code rule by asset type
     *
     * @param assetType Asset type code
     * @return The code rule, or null if not found
     */
    AssetCodeRule getByAssetType(String assetType);

    /**
     * Get all enabled rules
     *
     * @return List of enabled rules
     */
    List<AssetCodeRule> getAllEnabled();

    /**
     * Refresh rule cache
     */
    void refreshCache();

    /**
     * Save or update a code rule
     *
     * @param rule The rule to save
     * @return Saved rule
     */
    AssetCodeRule save(AssetCodeRule rule);

    /**
     * Delete a code rule
     *
     * @param id Rule ID
     * @return true if deleted
     */
    boolean delete(Long id);
}
