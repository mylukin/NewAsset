package com.ruoyi.asset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.asset.domain.entity.Asset;
import com.ruoyi.asset.domain.entity.AssetHouse;
import com.ruoyi.asset.domain.enums.AssetStatusEnum;
import com.ruoyi.asset.domain.vo.AssetHouseCreateDTO;
import com.ruoyi.asset.domain.vo.AssetHouseUpdateDTO;
import com.ruoyi.asset.domain.vo.AssetHouseQueryDTO;
import com.ruoyi.asset.domain.vo.AssetHouseVO;
import com.ruoyi.asset.domain.vo.HouseStatsVO;
import com.ruoyi.asset.domain.vo.UsageDistributionVO;
import com.ruoyi.asset.mapper.AssetHouseMapper;
import com.ruoyi.asset.mapper.AssetMapper;
import com.ruoyi.asset.service.IAssetHouseService;
import com.ruoyi.asset.service.rule.AssetCodeGenerator;
import com.ruoyi.common.core.page.PageResult;
import com.ruoyi.common.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * AssetHouse Service Implementation
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AssetHouseServiceImpl extends ServiceImpl<AssetHouseMapper, AssetHouse> implements IAssetHouseService {

    private final AssetMapper assetMapper;
    private final AssetCodeGenerator assetCodeGenerator;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AssetHouseVO create(AssetHouseCreateDTO dto, Long operatorId) {
        // Validate required fields
        if (dto.getAssetId() == null) {
            throw new ServiceException("Asset ID is required");
        }

        // Get base asset
        Asset asset = assetMapper.selectById(dto.getAssetId());
        if (asset == null) {
            throw new ServiceException("Asset not found: " + dto.getAssetId());
        }

        // Create house extension
        AssetHouse assetHouse = new AssetHouse();
        assetHouse.setAssetId(dto.getAssetId());
        assetHouse.setBuildingArea(dto.getBuildingArea());
        assetHouse.setInnerArea(dto.getInnerArea());
        assetHouse.setHouseType(dto.getHouseType());
        assetHouse.setHouseUsage(dto.getHouseUsage());
        assetHouse.setCurrentUsage(dto.getCurrentUsage());
        assetHouse.setCurrentUser(dto.getCurrentUser());
        assetHouse.setContractNo(dto.getContractNo());
        assetHouse.setRentUnitPrice(dto.getRentUnitPrice());
        assetHouse.setRentTotal(dto.getRentTotal());
        assetHouse.setCreateBy(String.valueOf(operatorId));
        assetHouse.setCreateTime(LocalDateTime.now());
        assetHouse.setUpdateBy(String.valueOf(operatorId));
        assetHouse.setUpdateTime(LocalDateTime.now());

        // Insert to database
        baseMapper.insert(assetHouse);

        log.info("Created house extension for asset ID: {}", dto.getAssetId());

        // Return VO
        return getById(dto.getAssetId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AssetHouseVO update(AssetHouseUpdateDTO dto, Long operatorId) {
        // Get existing asset
        Asset asset = assetMapper.selectById(dto.getAssetId());
        if (asset == null) {
            throw new ServiceException("Asset not found: " + dto.getAssetId());
        }

        // Update base asset if needed (add fields as required)
        // For now, only update house extension

        // Update house extension
        AssetHouse assetHouse = baseMapper.selectByAssetId(dto.getAssetId());
        if (assetHouse == null) {
            // Create if not exists
            return create(AssetHouseCreateDTO.builder()
                    .assetId(dto.getAssetId())
                    .buildingArea(dto.getBuildingArea())
                    .innerArea(dto.getInnerArea())
                    .houseType(dto.getHouseType())
                    .houseUsage(dto.getHouseUsage())
                    .currentUsage(dto.getCurrentUsage())
                    .currentUser(dto.getCurrentUser())
                    .contractNo(dto.getContractNo())
                    .rentUnitPrice(dto.getRentUnitPrice())
                    .rentTotal(dto.getRentTotal())
                    .build(), operatorId);
        }

        assetHouse.setBuildingArea(dto.getBuildingArea());
        assetHouse.setInnerArea(dto.getInnerArea());
        assetHouse.setHouseType(dto.getHouseType());
        assetHouse.setHouseUsage(dto.getHouseUsage());
        assetHouse.setCurrentUsage(dto.getCurrentUsage());
        assetHouse.setCurrentUser(dto.getCurrentUser());
        assetHouse.setContractNo(dto.getContractNo());
        assetHouse.setRentUnitPrice(dto.getRentUnitPrice());
        assetHouse.setRentTotal(dto.getRentTotal());
        assetHouse.setUpdateBy(String.valueOf(operatorId));
        assetHouse.setUpdateTime(LocalDateTime.now());

        baseMapper.updateById(assetHouse);

        log.info("Updated house extension for asset ID: {}", dto.getAssetId());

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

            // Soft delete house extension
            AssetHouse assetHouse = baseMapper.selectByAssetId(id);
            if (assetHouse != null) {
                baseMapper.deleteByAssetId(id);
            }

            // Soft delete base asset
            assetMapper.deleteById(id);

            log.info("Deleted house asset with ID: {}", id);
        }
    }

    @Override
    public AssetHouseVO getById(Long id) {
        // Get base asset
        Asset asset = assetMapper.selectById(id);
        if (asset == null) {
            return null;
        }

        // Get house extension
        AssetHouse assetHouse = baseMapper.selectByAssetId(id);

        // Build VO
        AssetHouseVO vo = new AssetHouseVO();
        vo.setAssetId(id);
        vo.setBuildingArea(assetHouse != null ? assetHouse.getBuildingArea() : null);
        vo.setInnerArea(assetHouse != null ? assetHouse.getInnerArea() : null);
        vo.setHouseType(assetHouse != null ? assetHouse.getHouseType() : null);
        vo.setHouseUsage(assetHouse != null ? assetHouse.getHouseUsage() : null);
        vo.setCurrentUsage(assetHouse != null ? assetHouse.getCurrentUsage() : null);
        vo.setCurrentUser(assetHouse != null ? assetHouse.getCurrentUser() : null);
        vo.setContractNo(assetHouse != null ? assetHouse.getContractNo() : null);
        vo.setRentUnitPrice(assetHouse != null ? assetHouse.getRentUnitPrice() : null);
        vo.setRentTotal(assetHouse != null ? assetHouse.getRentTotal() : null);

        return vo;
    }

    @Override
    public PageResult<AssetHouseVO> list(AssetHouseQueryDTO query) {
        Page<AssetHouseVO> page = new Page<>(query.getPageNum(), query.getPageSize());

        // Build query wrapper
        LambdaQueryWrapper<Asset> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Asset::getAssetType, "HOUSE");

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
        Page<AssetHouseVO> voPage = new Page<>(result.getCurrent(), result.getSize(), result.getTotal());
        voPage.setRecords(result.getRecords().stream()
                .map(this::convertToVO)
                .collect(java.util.stream.Collectors.toList()));

        return new PageResult<>(voPage.getRecords(), voPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AssetHouseVO copy(Long sourceId, Long operatorId) {
        // Get source asset
        Asset sourceAsset = assetMapper.selectById(sourceId);
        if (sourceAsset == null) {
            throw new ServiceException("Source asset not found: " + sourceId);
        }

        // Create new asset (copy base fields)
        Asset newAsset = new Asset();
        newAsset.setName(sourceAsset.getName());
        newAsset.setAssetType(sourceAsset.getAssetType());
        newAsset.setStatus(AssetStatusEnum.AVAILABLE_IDLE.getCode());
        newAsset.setProjectId(sourceAsset.getProjectId());
        newAsset.setBuildingId(sourceAsset.getBuildingId());
        newAsset.setFloorId(sourceAsset.getFloorId());
        newAsset.setUnitId(sourceAsset.getUnitId());
        newAsset.setLocationDesc(sourceAsset.getLocationDesc());
        newAsset.setResponsiblePerson(sourceAsset.getResponsiblePerson());
        newAsset.setResponsiblePhone(sourceAsset.getResponsiblePhone());
        newAsset.setPurchaseDate(sourceAsset.getPurchaseDate());
        newAsset.setPurchasePrice(sourceAsset.getPurchasePrice());
        newAsset.setWarrantyExpireDate(sourceAsset.getWarrantyExpireDate());
        newAsset.setDescription(sourceAsset.getDescription());
        newAsset.setCreateBy(String.valueOf(operatorId));
        newAsset.setCreateTime(LocalDateTime.now());
        newAsset.setUpdateBy(String.valueOf(operatorId));
        newAsset.setUpdateTime(LocalDateTime.now());

        // Generate new asset code
        String newAssetCode = assetCodeGenerator.generate("HOUSE");
        newAsset.setAssetCode(newAssetCode);

        // Insert new asset
        assetMapper.insert(newAsset);

        // Copy house extension if exists
        AssetHouse sourceHouse = baseMapper.selectByAssetId(sourceId);
        if (sourceHouse != null) {
            AssetHouse newHouse = new AssetHouse();
            newHouse.setAssetId(newAsset.getId());
            newHouse.setBuildingArea(sourceHouse.getBuildingArea());
            newHouse.setInnerArea(sourceHouse.getInnerArea());
            newHouse.setHouseType(sourceHouse.getHouseType());
            newHouse.setHouseUsage(sourceHouse.getHouseUsage());
            // Clear these fields for new asset
            newHouse.setCurrentUsage(null);
            newHouse.setCurrentUser(null);
            newHouse.setContractNo(null);
            newHouse.setRentUnitPrice(sourceHouse.getRentUnitPrice());
            newHouse.setRentTotal(sourceHouse.getRentTotal());
            newHouse.setCreateBy(String.valueOf(operatorId));
            newHouse.setCreateTime(LocalDateTime.now());
            newHouse.setUpdateBy(String.valueOf(operatorId));
            newHouse.setUpdateTime(LocalDateTime.now());

            baseMapper.insert(newHouse);
        }

        log.info("Copied house asset from ID: {} to ID: {}", sourceId, newAsset.getId());

        return getById(newAsset.getId());
    }

    @Override
    public HouseStatsVO getStats(Long projectId) {
        // Get total count
        LambdaQueryWrapper<Asset> totalWrapper = new LambdaQueryWrapper<>();
        totalWrapper.eq(Asset::getAssetType, "HOUSE")
                    .eq(Asset::getDelFlag, "0");
        if (projectId != null) {
            totalWrapper.eq(Asset::getProjectId, projectId);
        }
        Integer totalCount = assetMapper.selectCount(totalWrapper);

        // Get self-use count
        Integer selfUseCount = getCountByUsage(projectId, "self_use");

        // Get rental count
        Integer rentalCount = getCountByUsage(projectId, "rented");

        // Get idle count
        Integer idleCount = getCountByUsage(projectId, "idle");

        // Calculate rates
        BigDecimal vacancyRate = BigDecimal.ZERO;
        BigDecimal rentalRate = BigDecimal.ZERO;

        if (totalCount > 0) {
            vacancyRate = new BigDecimal(idleCount)
                    .divide(new BigDecimal(totalCount), 2, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal(100));
            rentalRate = new BigDecimal(rentalCount)
                    .divide(new BigDecimal(totalCount), 2, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal(100));
        }

        HouseStatsVO stats = new HouseStatsVO();
        stats.setTotalCount(totalCount);
        stats.setSelfUseCount(selfUseCount);
        stats.setRentalCount(rentalCount);
        stats.setIdleCount(idleCount);
        stats.setVacancyRate(vacancyRate);
        stats.setRentalRate(rentalRate);

        return stats;
    }

    @Override
    public List<UsageDistributionVO> getUsageDistribution(Long projectId) {
        List<UsageDistributionVO> distribution = new ArrayList<>();

        // Get counts for each usage type
        Integer selfUseCount = getCountByUsage(projectId, "self_use");
        Integer rentalCount = getCountByUsage(projectId, "rented");
        Integer idleCount = getCountByUsage(projectId, "idle");

        Integer total = selfUseCount + rentalCount + idleCount;

        // Create distribution entries
        if (total > 0) {
            UsageDistributionVO selfUse = new UsageDistributionVO();
            selfUse.setUsageType("self_use");
            selfUse.setCount(selfUseCount);
            selfUse.setPercentage(new BigDecimal(selfUseCount)
                    .divide(new BigDecimal(total), 4, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal(100))
                    .doubleValue());
            distribution.add(selfUse);

            UsageDistributionVO rented = new UsageDistributionVO();
            rented.setUsageType("rented");
            rented.setCount(rentalCount);
            rented.setPercentage(new BigDecimal(rentalCount)
                    .divide(new BigDecimal(total), 4, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal(100))
                    .doubleValue());
            distribution.add(rented);

            UsageDistributionVO idle = new UsageDistributionVO();
            idle.setUsageType("idle");
            idle.setCount(idleCount);
            idle.setPercentage(new BigDecimal(idleCount)
                    .divide(new BigDecimal(total), 4, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal(100))
                    .doubleValue());
            distribution.add(idle);
        }

        return distribution;
    }

    private Integer getCountByUsage(Long projectId, String usage) {
        LambdaQueryWrapper<AssetHouse> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AssetHouse::getCurrentUsage, usage);

        if (projectId != null) {
            // Join with asset table to filter by project
            // For simplicity, we'll query all and filter in code
            // In production, this should be optimized with proper SQL joins
        }

        List<AssetHouse> houses = baseMapper.selectList(wrapper);
        if (projectId != null) {
            houses = houses.stream()
                    .filter(h -> {
                        Asset asset = assetMapper.selectById(h.getAssetId());
                        return asset != null && projectId.equals(asset.getProjectId());
                    })
                    .collect(java.util.stream.Collectors.toList());
        }

        return houses.size();
    }

    private AssetHouseVO convertToVO(Asset asset) {
        AssetHouseVO vo = new AssetHouseVO();
        vo.setAssetId(asset.getId());
        // Set base asset fields as needed
        return vo;
    }
}
