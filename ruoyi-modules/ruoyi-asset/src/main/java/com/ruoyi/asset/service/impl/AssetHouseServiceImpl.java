package com.ruoyi.asset.service.impl;

import com.ruoyi.asset.domain.dto.AssetHouseCreateDTO;
import com.ruoyi.asset.domain.dto.AssetHouseUpdateDTO;
import com.ruoyi.asset.domain.entity.Asset;
import com.ruoyi.asset.domain.entity.AssetHouse;
import com.ruoyi.asset.domain.enums.AssetStatusEnum;
import com.ruoyi.asset.domain.vo.AssetHouseDetailVO;
import com.ruoyi.asset.domain.vo.AssetHouseListVO;
import com.ruoyi.asset.domain.vo.LocationInfo;
import com.ruoyi.asset.mapper.AssetHouseMapper;
import com.ruoyi.asset.mapper.AssetHouseMapper.AssetHouseQuery;
import com.ruoyi.asset.mapper.AssetMapper;
import com.ruoyi.asset.service.AssetCodeGenerator;
import com.ruoyi.asset.service.IAssetHouseService;
import com.ruoyi.asset.service.rule.AssetStatusService;
import com.ruoyi.common.datascope.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * House Asset Service Implementation
 */
@Service
public class AssetHouseServiceImpl implements IAssetHouseService {

    private static final String ASSET_TYPE_HOUSE = "house";

    @Autowired
    private AssetHouseMapper assetHouseMapper;

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private AssetCodeGenerator assetCodeGenerator;

    @Autowired
    private AssetStatusService assetStatusService;

    @Override
    @DataScope(projectAlias = "a", deptAlias = "a", userAlias = "a")
    public List<AssetHouseListVO> selectAssetHouseList(AssetHouseQuery query) {
        return assetHouseMapper.selectAssetHouseList(query);
    }

    @Override
    public AssetHouseDetailVO selectAssetHouseById(Long id) {
        return assetHouseMapper.selectAssetHouseById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertAssetHouse(AssetHouseCreateDTO createDTO) {
        // Create base asset record
        Asset asset = new Asset();
        asset.setAssetName(createDTO.getAssetName());
        asset.setAssetType(ASSET_TYPE_HOUSE);
        asset.setProjectId(createDTO.getProjectId());
        asset.setBuilding(createDTO.getBuilding());
        asset.setFloor(createDTO.getFloor());
        asset.setRoomNo(createDTO.getRoomNo());
        asset.setLocationDesc(createDTO.getLocationDesc());
        asset.setOwnershipType(createDTO.getOwnershipType());
        asset.setOwnerOrg(createDTO.getOwnerOrg());
        asset.setUseDeptId(createDTO.getUseDeptId());
        asset.setDutyUserId(createDTO.getDutyUserId());
        asset.setPurchaseDate(createDTO.getPurchaseDate());
        asset.setStartUseDate(createDTO.getStartUseDate());
        asset.setOriginalValue(createDTO.getOriginalValue());
        asset.setDepreciationMethod(createDTO.getDepreciationMethod());
        asset.setBrand(createDTO.getBrand());
        asset.setModel(createDTO.getModel());
        asset.setSupplier(createDTO.getSupplier());
        asset.setWarrantyExpireDate(createDTO.getWarrantyExpireDate());
        asset.setRemark(createDTO.getRemark());

        // Generate asset code
        LocationInfo locationInfo = new LocationInfo(
                createDTO.getBuilding(),
                createDTO.getFloor(),
                createDTO.getRoomNo()
        );
        String assetCode = assetCodeGenerator.generateWithRetry(
                ASSET_TYPE_HOUSE,
                createDTO.getProjectId(),
                locationInfo,
                3
        );
        asset.setAssetCode(assetCode);

        // Set initial status
        asset.setStatus(AssetStatusEnum.IDLE.getCode());
        asset.setDelFlag("0");

        // Insert base asset
        assetMapper.insert(asset);

        // Create house extension record
        AssetHouse assetHouse = new AssetHouse();
        assetHouse.setAssetId(asset.getId());
        assetHouse.setBuildingArea(createDTO.getBuildingArea());
        assetHouse.setInnerArea(createDTO.getInnerArea());
        assetHouse.setHouseType(createDTO.getHouseType());
        assetHouse.setHouseUsage(createDTO.getHouseUsage());
        assetHouse.setCurrentUsage(createDTO.getCurrentUsage());
        assetHouse.setCurrentUser(createDTO.getCurrentUser());
        assetHouse.setContractNo(createDTO.getContractNo());
        assetHouse.setRentUnitPrice(createDTO.getRentUnitPrice());
        assetHouse.setRentTotal(createDTO.getRentTotal());

        assetHouseMapper.insertAssetHouse(assetHouse);

        return asset.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateAssetHouse(AssetHouseUpdateDTO updateDTO) {
        // Update base asset record
        Asset asset = assetMapper.selectById(updateDTO.getId());
        if (asset == null) {
            throw new RuntimeException("Asset not found: " + updateDTO.getId());
        }

        asset.setAssetName(updateDTO.getAssetName());
        asset.setBuilding(updateDTO.getBuilding());
        asset.setFloor(updateDTO.getFloor());
        asset.setRoomNo(updateDTO.getRoomNo());
        asset.setLocationDesc(updateDTO.getLocationDesc());
        asset.setOwnershipType(updateDTO.getOwnershipType());
        asset.setOwnerOrg(updateDTO.getOwnerOrg());
        asset.setUseDeptId(updateDTO.getUseDeptId());
        asset.setDutyUserId(updateDTO.getDutyUserId());
        asset.setPurchaseDate(updateDTO.getPurchaseDate());
        asset.setStartUseDate(updateDTO.getStartUseDate());
        asset.setOriginalValue(updateDTO.getOriginalValue());
        asset.setDepreciationMethod(updateDTO.getDepreciationMethod());
        asset.setBrand(updateDTO.getBrand());
        asset.setModel(updateDTO.getModel());
        asset.setSupplier(updateDTO.getSupplier());
        asset.setWarrantyExpireDate(updateDTO.getWarrantyExpireDate());
        asset.setRemark(updateDTO.getRemark());

        assetMapper.updateById(asset);

        // Update house extension record
        AssetHouse assetHouse = new AssetHouse();
        assetHouse.setAssetId(updateDTO.getId());
        assetHouse.setBuildingArea(updateDTO.getBuildingArea());
        assetHouse.setInnerArea(updateDTO.getInnerArea());
        assetHouse.setHouseType(updateDTO.getHouseType());
        assetHouse.setHouseUsage(updateDTO.getHouseUsage());
        assetHouse.setCurrentUsage(updateDTO.getCurrentUsage());
        assetHouse.setCurrentUser(updateDTO.getCurrentUser());
        assetHouse.setContractNo(updateDTO.getContractNo());
        assetHouse.setRentUnitPrice(updateDTO.getRentUnitPrice());
        assetHouse.setRentTotal(updateDTO.getRentTotal());

        return assetHouseMapper.updateAssetHouse(assetHouse);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAssetHouseById(Long id) {
        // Soft delete base asset
        Asset asset = assetMapper.selectById(id);
        if (asset != null) {
            asset.setDelFlag("2");
            assetMapper.updateById(asset);
        }
        // Delete house extension
        return assetHouseMapper.deleteAssetHouseById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAssetHouseByIds(Long[] ids) {
        // Soft delete base assets
        for (Long id : ids) {
            Asset asset = assetMapper.selectById(id);
            if (asset != null) {
                asset.setDelFlag("2");
                assetMapper.updateById(asset);
            }
        }
        // Delete house extensions
        return assetHouseMapper.deleteAssetHouseByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int changeStatus(Long id, String newStatus, String reason) {
        AssetStatusEnum statusEnum = AssetStatusEnum.fromCode(newStatus);
        if (statusEnum == null) {
            throw new IllegalArgumentException("Invalid status code: " + newStatus);
        }

        // Use state machine service for status change
        // TODO: Get operator ID from security context
        Long operatorId = 1L;
        assetStatusService.changeStatus(id, statusEnum, reason, operatorId);
        return 1;
    }
}
