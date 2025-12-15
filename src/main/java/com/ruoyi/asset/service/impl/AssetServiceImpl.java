package com.ruoyi.asset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.asset.domain.entity.Asset;
import com.ruoyi.asset.domain.enums.AssetStatusEnum;
import com.ruoyi.asset.domain.vo.AssetCreateDTO;
import com.ruoyi.asset.domain.vo.AssetUpdateDTO;
import com.ruoyi.asset.domain.vo.AssetQueryDTO;
import com.ruoyi.asset.domain.vo.AssetVO;
import com.ruoyi.asset.mapper.AssetMapper;
import com.ruoyi.asset.service.IAssetService;
import com.ruoyi.asset.service.rule.AssetCodeGenerator;
import com.ruoyi.asset.service.AssetStatusService;
import com.ruoyi.common.annotation.DataScope;
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
 * Asset Service Implementation
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AssetServiceImpl extends ServiceImpl<AssetMapper, Asset> implements IAssetService {

    private final AssetCodeGenerator assetCodeGenerator;
    private final AssetStatusService assetStatusService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Asset create(AssetCreateDTO dto, Long operatorId) {
        // Validate required fields
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new ServiceException("Asset name is required");
        }

        if (dto.getAssetType() == null || dto.getAssetType().trim().isEmpty()) {
            throw new ServiceException("Asset type is required");
        }

        if (dto.getProjectId() == null) {
            throw new ServiceException("Project ID is required");
        }

        // Generate asset code
        String assetCode = assetCodeGenerator.generate(dto.getAssetType());

        // Create asset entity
        Asset asset = new Asset();
        asset.setName(dto.getName());
        asset.setAssetCode(assetCode);
        asset.setAssetType(dto.getAssetType());
        asset.setStatus(dto.getStatus() != null ? dto.getStatus() : AssetStatusEnum.AVAILABLE_IDLE.getCode());
        asset.setProjectId(dto.getProjectId());
        asset.setBuildingId(dto.getBuildingId());
        asset.setFloorId(dto.getFloorId());
        asset.setUnitId(dto.getUnitId());
        asset.setLocationDesc(dto.getLocationDesc());
        asset.setResponsiblePerson(dto.getResponsiblePerson());
        asset.setResponsiblePhone(dto.getResponsiblePhone());
        asset.setPurchaseDate(dto.getPurchaseDate());
        asset.setPurchasePrice(dto.getPurchasePrice());
        asset.setWarrantyExpireDate(dto.getWarrantyExpireDate());
        asset.setDescription(dto.getDescription());
        asset.setRemark(dto.getRemark());
        asset.setCreateBy(String.valueOf(operatorId));
        asset.setCreateTime(LocalDateTime.now());
        asset.setUpdateBy(String.valueOf(operatorId));
        asset.setUpdateTime(LocalDateTime.now());

        // Insert to database
        baseMapper.insert(asset);

        log.info("Created asset with code: {}, ID: {}", assetCode, asset.getId());
        return asset;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Asset update(AssetUpdateDTO dto, Long operatorId) {
        // Get existing asset
        Asset existingAsset = baseMapper.selectById(dto.getId());
        if (existingAsset == null) {
            throw new ServiceException("Asset not found: " + dto.getId());
        }

        // Update asset
        Asset asset = new Asset();
        asset.setId(dto.getId());
        asset.setName(dto.getName());
        asset.setStatus(dto.getStatus());
        asset.setBuildingId(dto.getBuildingId());
        asset.setFloorId(dto.getFloorId());
        asset.setUnitId(dto.getUnitId());
        asset.setLocationDesc(dto.getLocationDesc());
        asset.setResponsiblePerson(dto.getResponsiblePerson());
        asset.setResponsiblePhone(dto.getResponsiblePhone());
        asset.setPurchaseDate(dto.getPurchaseDate());
        asset.setPurchasePrice(dto.getPurchasePrice());
        asset.setWarrantyExpireDate(dto.getWarrantyExpireDate());
        asset.setDescription(dto.getDescription());
        asset.setRemark(dto.getRemark());
        asset.setUpdateBy(String.valueOf(operatorId));
        asset.setUpdateTime(LocalDateTime.now());

        int rows = baseMapper.updateById(asset);
        if (rows == 0) {
            throw new ServiceException("Failed to update asset");
        }

        log.info("Updated asset with ID: {}", dto.getId());
        return baseMapper.selectById(dto.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids, Long operatorId) {
        for (Long id : ids) {
            // Check for open work orders
            // TODO: Implement work order check

            // Soft delete
            Asset asset = new Asset();
            asset.setId(id);
            asset.setDelFlag("2");
            asset.setUpdateBy(String.valueOf(operatorId));
            asset.setUpdateTime(LocalDateTime.now());

            baseMapper.updateById(asset);
        }

        log.info("Deleted {} assets", ids.length);
    }

    @Override
    public Asset getById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public AssetVO getVOById(Long id) {
        Asset asset = baseMapper.selectById(id);
        return convertToVO(asset);
    }

    @Override
    @DataScope(deptAlias = "project_id")
    public PageResult<AssetVO> list(AssetQueryDTO query) {
        Page<Asset> page = new Page<>(query.getPageNum(), query.getPageSize());

        LambdaQueryWrapper<Asset> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(query.getProjectId() != null, Asset::getProjectId, query.getProjectId());
        wrapper.eq(query.getAssetType() != null, Asset::getAssetType, query.getAssetType());
        wrapper.eq(query.getStatus() != null, Asset::getStatus, query.getStatus());
        wrapper.eq(query.getBuildingId() != null, Asset::getBuildingId, query.getBuildingId());
        wrapper.eq(query.getFloorId() != null, Asset::getFloorId, query.getFloorId());

        if (query.getKeyword() != null && !query.getKeyword().trim().isEmpty()) {
            wrapper.and(w -> w.like(Asset::getName, query.getKeyword())
                .or().like(Asset::getAssetCode, query.getKeyword()));
        }

        wrapper.orderByDesc(Asset::getId);

        Page<Asset> result = baseMapper.selectPage(page, wrapper);

        List<AssetVO> voList = result.getRecords().stream()
            .map(this::convertToVO)
            .collect(Collectors.toList());

        return PageResult.of(result.getCurrent(), result.getSize(), result.getTotal(), voList);
    }

    /**
     * Convert Asset to AssetVO
     */
    private AssetVO convertToVO(Asset asset) {
        if (asset == null) {
            return null;
        }

        AssetVO vo = new AssetVO();
        vo.setId(asset.getId());
        vo.setAssetCode(asset.getAssetCode());
        vo.setName(asset.getName());
        vo.setAssetType(asset.getAssetType());
        vo.setStatus(asset.getStatus());
        vo.setProjectId(asset.getProjectId());
        vo.setBuildingId(asset.getBuildingId());
        vo.setFloorId(asset.getFloorId());
        vo.setUnitId(asset.getUnitId());
        vo.setLocationDesc(asset.getLocationDesc());
        vo.setResponsiblePerson(asset.getResponsiblePerson());
        vo.setResponsiblePhone(asset.getResponsiblePhone());
        vo.setPurchaseDate(asset.getPurchaseDate());
        vo.setPurchasePrice(asset.getPurchasePrice());
        vo.setWarrantyExpireDate(asset.getWarrantyExpireDate());
        vo.setDescription(asset.getDescription());
        vo.setStatusChangedAt(asset.getStatusChangedAt());
        vo.setStatusChangedBy(asset.getStatusChangedBy());
        vo.setDelFlag(asset.getDelFlag());
        vo.setCreateBy(asset.getCreateBy());
        vo.setCreateTime(asset.getCreateTime());
        vo.setUpdateBy(asset.getUpdateBy());
        vo.setUpdateTime(asset.getUpdateTime());
        vo.setRemark(asset.getRemark());

        return vo;
    }
}
