package com.ruoyi.asset.service.impl;

import com.ruoyi.asset.domain.entity.AssetCodeRule;
import com.ruoyi.asset.mapper.AssetCodeRuleMapper;
import com.ruoyi.asset.service.AssetCodeRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Asset code rule service implementation with caching
 */
@Service
public class AssetCodeRuleServiceImpl implements AssetCodeRuleService {

    private static final Logger log = LoggerFactory.getLogger(AssetCodeRuleServiceImpl.class);

    @Autowired
    private AssetCodeRuleMapper codeRuleMapper;

    /**
     * Cache for code rules by asset type
     */
    private final Map<String, AssetCodeRule> ruleCache = new ConcurrentHashMap<>();

    /**
     * Initialize cache on startup
     */
    @PostConstruct
    public void init() {
        refreshCache();
    }

    @Override
    public AssetCodeRule getByAssetType(String assetType) {
        // Try cache first
        AssetCodeRule rule = ruleCache.get(assetType);
        if (rule != null) {
            return rule;
        }
        // Load from database if not in cache
        rule = codeRuleMapper.findEnabledByAssetType(assetType);
        if (rule != null) {
            ruleCache.put(assetType, rule);
        }
        return rule;
    }

    @Override
    public List<AssetCodeRule> getAllEnabled() {
        return codeRuleMapper.findAllEnabled();
    }

    @Override
    public void refreshCache() {
        log.info("Refreshing asset code rule cache");
        ruleCache.clear();
        List<AssetCodeRule> rules = codeRuleMapper.findAllEnabled();
        for (AssetCodeRule rule : rules) {
            ruleCache.put(rule.getAssetType(), rule);
        }
        log.info("Loaded {} code rules into cache", rules.size());
    }

    @Override
    public AssetCodeRule save(AssetCodeRule rule) {
        if (rule.getId() == null) {
            codeRuleMapper.insert(rule);
        } else {
            codeRuleMapper.updateById(rule);
        }
        // Refresh cache after save
        if (rule.getEnabled()) {
            ruleCache.put(rule.getAssetType(), rule);
        } else {
            ruleCache.remove(rule.getAssetType());
        }
        return rule;
    }

    @Override
    public boolean delete(Long id) {
        AssetCodeRule rule = codeRuleMapper.selectById(id);
        if (rule != null) {
            int result = codeRuleMapper.deleteById(id);
            if (result > 0) {
                ruleCache.remove(rule.getAssetType());
                return true;
            }
        }
        return false;
    }
}
