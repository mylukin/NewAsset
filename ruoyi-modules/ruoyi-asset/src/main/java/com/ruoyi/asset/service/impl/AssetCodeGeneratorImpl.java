package com.ruoyi.asset.service.impl;

import com.ruoyi.asset.domain.entity.AssetCodeRule;
import com.ruoyi.asset.domain.entity.AssetCodeSeq;
import com.ruoyi.asset.domain.vo.LocationInfo;
import com.ruoyi.asset.mapper.AssetCodeSeqMapper;
import com.ruoyi.asset.service.AssetCodeGenerator;
import com.ruoyi.asset.service.AssetCodeRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * Asset code generator implementation
 * Supports pattern placeholders: {PROJECT}, {TYPE}, {BUILDING}, {FLOOR}, {SEQ}
 * Handles sequence reset by project/year scope with optimistic locking
 */
@Service
public class AssetCodeGeneratorImpl implements AssetCodeGenerator {

    private static final Logger log = LoggerFactory.getLogger(AssetCodeGeneratorImpl.class);

    private static final int DEFAULT_MAX_RETRIES = 3;

    @Autowired
    private AssetCodeRuleService codeRuleService;

    @Autowired
    private AssetCodeSeqMapper codeSeqMapper;

    @Override
    public String generate(String assetType, Long projectId, LocationInfo locationInfo) {
        return generateWithRetry(assetType, projectId, locationInfo, DEFAULT_MAX_RETRIES);
    }

    @Override
    public String preview(String assetType, Long projectId, LocationInfo locationInfo) {
        AssetCodeRule rule = getRule(assetType);
        Long nextSeq = getNextSequencePreview(rule, projectId);
        return buildCode(rule, projectId, locationInfo, nextSeq);
    }

    @Override
    @Transactional
    public String generateWithRetry(String assetType, Long projectId, LocationInfo locationInfo, int maxRetries) {
        AssetCodeRule rule = getRule(assetType);

        int attempts = 0;
        while (attempts < maxRetries) {
            try {
                Long nextSeq = incrementSequence(rule, projectId);
                return buildCode(rule, projectId, locationInfo, nextSeq);
            } catch (OptimisticLockingFailureException e) {
                attempts++;
                log.warn("Optimistic locking failure on sequence generation, attempt {}/{}", attempts, maxRetries);
                if (attempts >= maxRetries) {
                    throw new RuntimeException("Failed to generate asset code after " + maxRetries + " attempts", e);
                }
                // Small delay before retry
                try {
                    Thread.sleep(10 * attempts);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Interrupted during retry", ie);
                }
            }
        }
        throw new RuntimeException("Failed to generate asset code");
    }

    private AssetCodeRule getRule(String assetType) {
        AssetCodeRule rule = codeRuleService.getByAssetType(assetType);
        if (rule == null) {
            throw new IllegalArgumentException("No code rule found for asset type: " + assetType);
        }
        return rule;
    }

    /**
     * Get next sequence number preview without incrementing
     */
    private Long getNextSequencePreview(AssetCodeRule rule, Long projectId) {
        Integer year = getYearForScope(rule.getResetScope());
        Long scopeProjectId = getScopeProjectId(rule.getResetScope(), projectId);

        AssetCodeSeq seq = codeSeqMapper.findByRuleAndScope(rule.getId(), scopeProjectId, year);
        if (seq == null) {
            return 1L;
        }
        return seq.getCurrentSeq() + 1;
    }

    /**
     * Increment sequence with optimistic locking
     */
    private Long incrementSequence(AssetCodeRule rule, Long projectId) {
        Integer year = getYearForScope(rule.getResetScope());
        Long scopeProjectId = getScopeProjectId(rule.getResetScope(), projectId);

        AssetCodeSeq seq = codeSeqMapper.findByRuleAndScope(rule.getId(), scopeProjectId, year);

        if (seq == null) {
            // Create new sequence
            seq = new AssetCodeSeq();
            seq.setRuleId(rule.getId());
            seq.setProjectId(scopeProjectId);
            seq.setYear(year);
            seq.setCurrentSeq(1L);
            seq.setVersion(0);
            codeSeqMapper.insert(seq);
            return 1L;
        } else {
            // Update existing sequence with optimistic lock
            Long nextSeq = seq.getCurrentSeq() + 1;
            seq.setCurrentSeq(nextSeq);
            int updated = codeSeqMapper.updateById(seq);
            if (updated == 0) {
                throw new OptimisticLockingFailureException("Concurrent sequence update detected");
            }
            return nextSeq;
        }
    }

    /**
     * Get year for sequence scope
     */
    private Integer getYearForScope(String resetScope) {
        if ("BY_PROJECT_YEAR".equals(resetScope)) {
            return LocalDate.now().getYear();
        }
        return null;
    }

    /**
     * Get project ID for sequence scope
     */
    private Long getScopeProjectId(String resetScope, Long projectId) {
        if ("BY_PROJECT".equals(resetScope) || "BY_PROJECT_YEAR".equals(resetScope)) {
            return projectId;
        }
        return null;
    }

    /**
     * Build the code string from pattern
     */
    private String buildCode(AssetCodeRule rule, Long projectId, LocationInfo locationInfo, Long seq) {
        String code = rule.getPattern();
        String separator = rule.getSeparator() != null ? rule.getSeparator() : "-";

        // Replace placeholders
        code = code.replace("{PROJECT}", formatValue(projectId != null ? projectId.toString() : "", separator));
        code = code.replace("{TYPE}", formatValue(rule.getAssetType(), separator));

        if (locationInfo != null) {
            code = code.replace("{BUILDING}", formatValue(locationInfo.getBuilding(), separator));
            code = code.replace("{FLOOR}", formatValue(locationInfo.getFloor(), separator));
            code = code.replace("{ROOM}", formatValue(locationInfo.getRoomNo(), separator));
        } else {
            code = code.replace("{BUILDING}", "");
            code = code.replace("{FLOOR}", "");
            code = code.replace("{ROOM}", "");
        }

        // Format sequence number with zero-padding
        int seqLength = rule.getSeqLength() != null ? rule.getSeqLength() : 4;
        String seqStr = String.format("%0" + seqLength + "d", seq);
        code = code.replace("{SEQ}", seqStr);

        // Add year placeholder support
        code = code.replace("{YEAR}", String.valueOf(LocalDate.now().getYear()));
        code = code.replace("{YEAR2}", String.valueOf(LocalDate.now().getYear() % 100));

        // Clean up multiple separators
        while (code.contains(separator + separator)) {
            code = code.replace(separator + separator, separator);
        }
        // Remove leading/trailing separators
        if (code.startsWith(separator)) {
            code = code.substring(separator.length());
        }
        if (code.endsWith(separator)) {
            code = code.substring(0, code.length() - separator.length());
        }

        return code;
    }

    /**
     * Format a value, returning empty string if null or empty
     */
    private String formatValue(String value, String separator) {
        if (value == null || value.trim().isEmpty()) {
            return "";
        }
        return value.trim();
    }
}
