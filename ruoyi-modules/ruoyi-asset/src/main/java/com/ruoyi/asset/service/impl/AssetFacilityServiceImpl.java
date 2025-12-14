package com.ruoyi.asset.service.impl;

import com.ruoyi.asset.domain.dto.AssetFacilityCreateDTO;
import com.ruoyi.asset.domain.dto.AssetFacilityUpdateDTO;
import com.ruoyi.asset.domain.entity.Asset;
import com.ruoyi.asset.domain.entity.AssetFacility;
import com.ruoyi.asset.domain.enums.AssetStatusEnum;
import com.ruoyi.asset.domain.vo.AssetFacilityDetailVO;
import com.ruoyi.asset.domain.vo.AssetFacilityListVO;
import com.ruoyi.asset.domain.vo.FacilityWarrantyExpiringVO;
import com.ruoyi.asset.domain.vo.LocationInfo;
import com.ruoyi.asset.mapper.AssetFacilityMapper;
import com.ruoyi.asset.mapper.AssetFacilityMapper.AssetFacilityQuery;
import com.ruoyi.asset.mapper.AssetMapper;
import com.ruoyi.asset.service.AssetCodeGenerator;
import com.ruoyi.asset.service.IAssetFacilityService;
import com.ruoyi.asset.service.rule.AssetStatusService;
import com.ruoyi.common.datascope.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Facility Asset Service Implementation
 */
@Service
public class AssetFacilityServiceImpl implements IAssetFacilityService {

    private static final String ASSET_TYPE_FACILITY = "facility";

    @Autowired
    private AssetFacilityMapper assetFacilityMapper;

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private AssetCodeGenerator assetCodeGenerator;

    @Autowired
    private AssetStatusService assetStatusService;

    @Override
    @DataScope(projectAlias = "a", deptAlias = "a", userAlias = "a")
    public List<AssetFacilityListVO> selectAssetFacilityList(AssetFacilityQuery query) {
        return assetFacilityMapper.selectAssetFacilityList(query);
    }

    @Override
    public AssetFacilityDetailVO selectAssetFacilityById(Long id) {
        return assetFacilityMapper.selectAssetFacilityById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertAssetFacility(AssetFacilityCreateDTO createDTO) {
        // Create base asset record
        Asset asset = new Asset();
        asset.setAssetName(createDTO.getAssetName());
        asset.setAssetType(ASSET_TYPE_FACILITY);
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
                ASSET_TYPE_FACILITY,
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

        // Create facility extension record
        AssetFacility assetFacility = new AssetFacility();
        assetFacility.setAssetId(asset.getId());
        assetFacility.setFacilityType(createDTO.getFacilityType());
        assetFacility.setInstallPosition(createDTO.getInstallPosition());
        assetFacility.setEquipmentNo(createDTO.getEquipmentNo());
        assetFacility.setMaintOrg(createDTO.getMaintOrg());
        assetFacility.setMaintPhone(createDTO.getMaintPhone());

        assetFacilityMapper.insertAssetFacility(assetFacility);

        return asset.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateAssetFacility(AssetFacilityUpdateDTO updateDTO) {
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

        // Update facility extension record
        AssetFacility assetFacility = new AssetFacility();
        assetFacility.setAssetId(updateDTO.getId());
        assetFacility.setFacilityType(updateDTO.getFacilityType());
        assetFacility.setInstallPosition(updateDTO.getInstallPosition());
        assetFacility.setEquipmentNo(updateDTO.getEquipmentNo());
        assetFacility.setMaintOrg(updateDTO.getMaintOrg());
        assetFacility.setMaintPhone(updateDTO.getMaintPhone());

        return assetFacilityMapper.updateAssetFacility(assetFacility);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAssetFacilityById(Long id) {
        // Soft delete base asset
        Asset asset = assetMapper.selectById(id);
        if (asset != null) {
            asset.setDelFlag("2");
            assetMapper.updateById(asset);
        }
        // Delete facility extension
        return assetFacilityMapper.deleteAssetFacilityById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAssetFacilityByIds(Long[] ids) {
        // Soft delete base assets
        for (Long id : ids) {
            Asset asset = assetMapper.selectById(id);
            if (asset != null) {
                asset.setDelFlag("2");
                assetMapper.updateById(asset);
            }
        }
        // Delete facility extensions
        return assetFacilityMapper.deleteAssetFacilityByIds(ids);
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

    @Override
    public List<FacilityWarrantyExpiringVO> getExpiringFacilities(int daysAhead, Long projectId) {
        return assetFacilityMapper.selectExpiringFacilities(daysAhead, projectId);
    }
}
