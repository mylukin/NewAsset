package com.ruoyi.asset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.asset.domain.entity.Asset;
import com.ruoyi.asset.domain.entity.AssetParking;
import com.ruoyi.asset.domain.enums.AssetStatusEnum;
import com.ruoyi.asset.domain.vo.AssetParkingCreateDTO;
import com.ruoyi.asset.domain.vo.AssetParkingUpdateDTO;
import com.ruoyi.asset.domain.vo.AssetParkingQueryDTO;
import com.ruoyi.asset.domain.vo.AssetParkingVO;
import com.ruoyi.asset.mapper.AssetParkingMapper;
import com.ruoyi.asset.mapper.AssetMapper;
import com.ruoyi.asset.service.IAssetParkingService;
import com.ruoyi.asset.service.rule.AssetCodeGenerator;
import com.ruoyi.common.core.page.PageResult;
import com.ruoyi.common.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AssetParking Service Implementation
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AssetParkingServiceImpl extends ServiceImpl<AssetParkingMapper, AssetParking> implements IAssetParkingService {

    private final AssetMapper assetMapper;
    private final AssetCodeGenerator assetCodeGenerator;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AssetParkingVO create(AssetParkingCreateDTO dto, Long operatorId) {
        // Validate required fields
        if (dto.getAssetId() == null) {
            throw new ServiceException("Asset ID is required");
        }

        // Get base asset
        Asset asset = assetMapper.selectById(dto.getAssetId());
        if (asset == null) {
            throw new ServiceException("Asset not found: " + dto.getAssetId());
        }

        // Create parking extension
        AssetParking assetParking = new AssetParking();
        assetParking.setAssetId(dto.getAssetId());
        assetParking.setParkingNo(dto.getParkingNo());
        assetParking.setParkingZone(dto.getParkingZone());
        assetParking.setParkingType(dto.getParkingType());
        assetParking.setArea(dto.getArea());
        assetParking.setCurrentUser(dto.getCurrentUser());
        assetParking.setPlateNo(dto.getPlateNo());
        assetParking.setRentPrice(dto.getRentPrice());
        assetParking.setContractNo(dto.getContractNo());
        assetParking.setCreateBy(String.valueOf(operatorId));
        assetParking.setCreateTime(LocalDateTime.now());
        assetParking.setUpdateBy(String.valueOf(operatorId));
        assetParking.setUpdateTime(LocalDateTime.now());

        // Insert to database
        baseMapper.insert(assetParking);

        log.info("Created parking extension for asset ID: {}", dto.getAssetId());

        // Return VO
        return getById(dto.getAssetId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AssetParkingVO update(AssetParkingUpdateDTO dto, Long operatorId) {
        // Get existing asset
        Asset asset = assetMapper.selectById(dto.getAssetId());
        if (asset == null) {
            throw new ServiceException("Asset not found: " + dto.getAssetId());
        }

        // Update parking extension
        AssetParking assetParking = baseMapper.selectByAssetId(dto.getAssetId());
        if (assetParking == null) {
            // Create if not exists
            return create(AssetParkingCreateDTO.builder()
                    .assetId(dto.getAssetId())
                    .parkingNo(dto.getParkingNo())
                    .parkingZone(dto.getParkingZone())
                    .parkingType(dto.getParkingType())
                    .area(dto.getArea())
                    .currentUser(dto.getCurrentUser())
                    .plateNo(dto.getPlateNo())
                    .rentPrice(dto.getRentPrice())
                    .contractNo(dto.getContractNo())
                    .build(), operatorId);
        }

        assetParking.setParkingNo(dto.getParkingNo());
        assetParking.setParkingZone(dto.getParkingZone());
        assetParking.setParkingType(dto.getParkingType());
        assetParking.setArea(dto.getArea());
        assetParking.setCurrentUser(dto.getCurrentUser());
        assetParking.setPlateNo(dto.getPlateNo());
        assetParking.setRentPrice(dto.getRentPrice());
        assetParking.setContractNo(dto.getContractNo());
        assetParking.setUpdateBy(String.valueOf(operatorId));
        assetParking.setUpdateTime(LocalDateTime.now());

        baseMapper.updateById(assetParking);

        log.info("Updated parking extension for asset ID: {}", dto.getAssetId());

        return getById(dto.getAssetId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids, Long operatorId) {
        for (Long id : ids) {
            Asset asset = assetMapper.selectById(id);
            if (asset == null) {
                continue;
            }

            // Check for open work orders if needed
            // For now, proceed with deletion

            // Soft delete parking extension
            AssetParking assetParking = baseMapper.selectByAssetId(id);
            if (assetParking != null) {
                baseMapper.deleteByAssetId(id);
            }

            // Soft delete base asset
            assetMapper.deleteById(id);

            log.info("Deleted parking asset with ID: {}", id);
        }
    }

    @Override
    public AssetParkingVO getById(Long id) {
        // Get base asset
        Asset asset = assetMapper.selectById(id);
        if (asset == null) {
            return null;
        }

        // Get parking extension
        AssetParking assetParking = baseMapper.selectByAssetId(id);

        // Build VO
        AssetParkingVO vo = new AssetParkingVO();
        vo.setAssetId(id);
        vo.setParkingNo(assetParking != null ? assetParking.getParkingNo() : null);
        vo.setParkingZone(assetParking != null ? assetParking.getParkingZone() : null);
        vo.setParkingType(assetParking != null ? assetParking.getParkingType() : null);
        vo.setArea(assetParking != null ? assetParking.getArea() : null);
        vo.setCurrentUser(assetParking != null ? assetParking.getCurrentUser() : null);
        vo.setPlateNo(assetParking != null ? assetParking.getPlateNo() : null);
        vo.setRentPrice(assetParking != null ? assetParking.getRentPrice() : null);
        vo.setContractNo(assetParking != null ? assetParking.getContractNo() : null);

        return vo;
    }

    @Override
    public PageResult<AssetParkingVO> list(AssetParkingQueryDTO query) {
        Page<AssetParkingVO> page = new Page<>(query.getPageNum(), query.getPageSize());

        // Build query wrapper
        LambdaQueryWrapper<Asset> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Asset::getAssetType, "PARKING");

        if (query.getProjectId() != null) {
            wrapper.eq(Asset::getProjectId, query.getProjectId());
        }
        if (query.getStatus() != null) {
            wrapper.eq(Asset::getStatus, query.getStatus());
        }
        if (query.getBuildingId() != null) {
            wrapper.eq(Asset::getBuildingId, query.getBuildingId());
        }
        if (query.getFloorId() != null) {
            wrapper.eq(Asset::getFloorId, query.getFloorId());
        }
        if (query.getKeyword() != null && !query.getKeyword().trim().isEmpty()) {
            wrapper.and(w -> w.like(Asset::getName, query.getKeyword())
                    .or().like(Asset::getAssetCode, query.getKeyword()));
        }

        // Execute query
        Page<Asset> result = assetMapper.selectPage(page, wrapper);

        // Convert to VO
        Page<AssetParkingVO> voPage = new Page<>(result.getCurrent(), result.getSize(), result.getTotal());
        voPage.setRecords(result.getRecords().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList()));

        return new PageResult<>(voPage.getRecords(), voPage.getTotal());
    }

    private AssetParkingVO convertToVO(Asset asset) {
        AssetParkingVO vo = new AssetParkingVO();
        vo.setAssetId(asset.getId());
        // Set base asset fields as needed
        return vo;
    }
}
