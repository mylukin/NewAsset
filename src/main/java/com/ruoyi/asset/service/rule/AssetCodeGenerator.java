package com.ruoyi.asset.service.rule;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.asset.domain.entity.Asset;
import com.ruoyi.asset.mapper.AssetMapper;
import com.ruoyi.asset.mapper.AssetCodeSeqMapper;
import com.ruoyi.asset.domain.entity.AssetCodeSeq;
import com.ruoyi.common.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Asset Code Generator Service
 *
 * Auto-generates unique asset codes with format: {PREFIX}-{6_DIGIT_SEQ}
 * Uses optimistic locking for concurrent generation safety
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AssetCodeGenerator {

    private final AssetCodeSeqMapper assetCodeSeqMapper;
    private final AssetMapper assetMapper;

    // Asset type to prefix mapping
    private static final Map<String, String> TYPE_PREFIX_MAP = new HashMap<>();

    static {
        TYPE_PREFIX_MAP.put("HOUSE", "HA");
        TYPE_PREFIX_MAP.put("PARKING", "PA");
        TYPE_PREFIX_MAP.put("FACILITY", "FA");
        TYPE_PREFIX_MAP.put("VENUE", "VE");
        TYPE_PREFIX_MAP.put("OFFICE", "OF");
    }

    private static final int MAX_RETRIES = 3;

    /**
     * Generate unique asset code for given asset type
     *
     * @param assetType asset type (HOUSE, PARKING, FACILITY, VENUE, OFFICE)
     * @return formatted asset code (e.g., HA-000001)
     */
    @Transactional(rollbackFor = Exception.class)
    public String generate(String assetType) {
        String prefix = TYPE_PREFIX_MAP.get(assetType);
        if (prefix == null) {
            throw new ServiceException("Invalid asset type: " + assetType);
        }

        int attempt = 0;
        while (attempt < MAX_RETRIES) {
            try {
                return generateWithLock(assetType, prefix);
            } catch (Exception e) {
                attempt++;
                if (attempt >= MAX_RETRIES) {
                    log.error("Failed to generate asset code for type {} after {} attempts", assetType, MAX_RETRIES);
                    throw new ServiceException("Failed to generate unique asset code after " + MAX_RETRIES + " attempts", e);
                }
                log.warn("Attempt {} failed to generate asset code for type {}, retrying...", attempt, assetType);
                try {
                    Thread.sleep(10 * attempt); // Exponential backoff
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new ServiceException("Code generation interrupted", ie);
                }
            }
        }

        throw new ServiceException("Failed to generate asset code");
    }

    /**
     * Generate code with optimistic locking
     */
    private String generateWithLock(String assetType, String prefix) {
        // Find or create sequence record
        AssetCodeSeq seqRecord = assetCodeSeqMapper.selectOne(
            new LambdaQueryWrapper<AssetCodeSeq>()
                .eq(AssetCodeSeq::getAssetType, assetType)
        );

        if (seqRecord == null) {
            // Create new sequence record
            seqRecord = new AssetCodeSeq();
            seqRecord.setAssetType(assetType);
            seqRecord.setPrefix(prefix);
            seqRecord.setCurrentSeq(0L);
            assetCodeSeqMapper.insert(seqRecord);
        }

        // Get current sequence and version
        Long currentSeq = seqRecord.getCurrentSeq();
        Integer currentVersion = seqRecord.getVersion();

        // Increment sequence
        Long newSeq = currentSeq + 1;

        // Update with version check (optimistic locking)
        int updatedRows = assetCodeSeqMapper.updateSeqWithVersion(
            assetType,
            newSeq,
            currentVersion
        );

        if (updatedRows == 0) {
            // Version conflict - someone else updated the record
            throw new RuntimeException("Version conflict - retry needed");
        }

        String code = String.format("%s-%06d", prefix, newSeq);

        // Check if code already exists (unlikely but possible)
        int existsCount = assetMapper.selectCount(
            new LambdaQueryWrapper<Asset>()
                .eq(Asset::getAssetCode, code)
        );

        if (existsCount > 0) {
            // Code collision - regenerate
            log.warn("Code collision detected for {}, regenerating...", code);
            return generate(assetType);
        }

        return code;
    }
}
