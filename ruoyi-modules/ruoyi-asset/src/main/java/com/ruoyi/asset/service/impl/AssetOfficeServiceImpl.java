package com.ruoyi.asset.service.impl;

import com.ruoyi.asset.domain.dto.AssetOfficeCreateDTO;
import com.ruoyi.asset.domain.dto.AssetOfficeUpdateDTO;
import com.ruoyi.asset.domain.entity.Asset;
import com.ruoyi.asset.domain.entity.AssetOffice;
import com.ruoyi.asset.domain.enums.AssetStatusEnum;
import com.ruoyi.asset.domain.vo.AssetOfficeDetailVO;
import com.ruoyi.asset.domain.vo.AssetOfficeListVO;
import com.ruoyi.asset.domain.vo.LocationInfo;
import com.ruoyi.asset.mapper.AssetOfficeMapper;
import com.ruoyi.asset.mapper.AssetOfficeMapper.AssetOfficeQuery;
import com.ruoyi.asset.mapper.AssetMapper;
import com.ruoyi.asset.service.AssetCodeGenerator;
import com.ruoyi.asset.service.IAssetOfficeService;
import com.ruoyi.asset.service.rule.AssetStatusService;
import com.ruoyi.common.datascope.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Office Asset Service Implementation
 */
@Service
public class AssetOfficeServiceImpl implements IAssetOfficeService {

    private static final String ASSET_TYPE_OFFICE = "OFFICE";

    @Autowired
    private AssetOfficeMapper assetOfficeMapper;

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private AssetCodeGenerator assetCodeGenerator;

    @Autowired
    private AssetStatusService assetStatusService;

    @Override
    @DataScope(projectAlias = "a", deptAlias = "a", userAlias = "a")
    public List<AssetOfficeListVO> selectAssetOfficeList(AssetOfficeQuery query) {
        return assetOfficeMapper.selectAssetOfficeList(query);
    }

    @Override
    public AssetOfficeDetailVO selectAssetOfficeById(Long id) {
        return assetOfficeMapper.selectAssetOfficeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertAssetOffice(AssetOfficeCreateDTO createDTO) {
        // Create base asset record
        Asset asset = new Asset();
        asset.setAssetName(createDTO.getAssetName());
        asset.setAssetType(ASSET_TYPE_OFFICE);
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
                ASSET_TYPE_OFFICE,
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

        // Create office extension record
        AssetOffice assetOffice = new AssetOffice();
        assetOffice.setAssetId(asset.getId());
        assetOffice.setOfficeType(createDTO.getOfficeType());
        assetOffice.setWorkplaceNo(createDTO.getWorkplaceNo());
        assetOffice.setUseUser(createDTO.getUseUser());
        assetOffice.setSerialNo(createDTO.getSerialNo());
        assetOffice.setOsInfo(createDTO.getOsInfo());
        assetOffice.setConfigDesc(createDTO.getConfigDesc());

        assetOfficeMapper.insertAssetOffice(assetOffice);

        return asset.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateAssetOffice(AssetOfficeUpdateDTO updateDTO) {
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

        // Update office extension record
        AssetOffice assetOffice = new AssetOffice();
        assetOffice.setAssetId(updateDTO.getId());
        assetOffice.setOfficeType(updateDTO.getOfficeType());
        assetOffice.setWorkplaceNo(updateDTO.getWorkplaceNo());
        assetOffice.setUseUser(updateDTO.getUseUser());
        assetOffice.setSerialNo(updateDTO.getSerialNo());
        assetOffice.setOsInfo(updateDTO.getOsInfo());
        assetOffice.setConfigDesc(updateDTO.getConfigDesc());

        return assetOfficeMapper.updateAssetOffice(assetOffice);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAssetOfficeById(Long id) {
        // Soft delete base asset
        Asset asset = assetMapper.selectById(id);
        if (asset != null) {
            asset.setDelFlag("2");
            assetMapper.updateById(asset);
        }
        // Delete office extension
        return assetOfficeMapper.deleteAssetOfficeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAssetOfficeByIds(Long[] ids) {
        // Soft delete base assets
        for (Long id : ids) {
            Asset asset = assetMapper.selectById(id);
            if (asset != null) {
                asset.setDelFlag("2");
                assetMapper.updateById(asset);
            }
        }
        // Delete office extensions
        return assetOfficeMapper.deleteAssetOfficeByIds(ids);
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
