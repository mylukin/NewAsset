package com.ruoyi.asset.service.impl;

import com.ruoyi.asset.domain.dto.AssetParkingCreateDTO;
import com.ruoyi.asset.domain.dto.AssetParkingUpdateDTO;
import com.ruoyi.asset.domain.entity.Asset;
import com.ruoyi.asset.domain.entity.AssetParking;
import com.ruoyi.asset.domain.enums.AssetStatusEnum;
import com.ruoyi.asset.domain.vo.AssetParkingDetailVO;
import com.ruoyi.asset.domain.vo.AssetParkingListVO;
import com.ruoyi.asset.domain.vo.LocationInfo;
import com.ruoyi.asset.domain.vo.ParkingStatisticsVO;
import com.ruoyi.asset.mapper.AssetParkingMapper;
import com.ruoyi.asset.mapper.AssetParkingMapper.AssetParkingQuery;
import com.ruoyi.asset.mapper.AssetMapper;
import com.ruoyi.asset.service.AssetCodeGenerator;
import com.ruoyi.asset.service.IAssetParkingService;
import com.ruoyi.asset.service.rule.AssetStatusService;
import com.ruoyi.common.datascope.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Parking Asset Service Implementation
 */
@Service
public class AssetParkingServiceImpl implements IAssetParkingService {

    private static final String ASSET_TYPE_PARKING = "parking";

    @Autowired
    private AssetParkingMapper assetParkingMapper;

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private AssetCodeGenerator assetCodeGenerator;

    @Autowired
    private AssetStatusService assetStatusService;

    @Override
    @DataScope(projectAlias = "a", deptAlias = "a", userAlias = "a")
    public List<AssetParkingListVO> selectAssetParkingList(AssetParkingQuery query) {
        return assetParkingMapper.selectAssetParkingList(query);
    }

    @Override
    public AssetParkingDetailVO selectAssetParkingById(Long id) {
        return assetParkingMapper.selectAssetParkingById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertAssetParking(AssetParkingCreateDTO createDTO) {
        // Create base asset record
        Asset asset = new Asset();
        asset.setAssetName(createDTO.getAssetName());
        asset.setAssetType(ASSET_TYPE_PARKING);
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
                ASSET_TYPE_PARKING,
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

        // Create parking extension record
        AssetParking assetParking = new AssetParking();
        assetParking.setAssetId(asset.getId());
        assetParking.setParkingNo(createDTO.getParkingNo());
        assetParking.setParkingZone(createDTO.getParkingZone());
        assetParking.setParkingType(createDTO.getParkingType());
        assetParking.setArea(createDTO.getArea());
        assetParking.setCurrentUser(createDTO.getCurrentUser());
        assetParking.setPlateNo(createDTO.getPlateNo());
        assetParking.setRentPrice(createDTO.getRentPrice());
        assetParking.setContractNo(createDTO.getContractNo());

        assetParkingMapper.insertAssetParking(assetParking);

        return asset.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateAssetParking(AssetParkingUpdateDTO updateDTO) {
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

        // Update parking extension record
        AssetParking assetParking = new AssetParking();
        assetParking.setAssetId(updateDTO.getId());
        assetParking.setParkingNo(updateDTO.getParkingNo());
        assetParking.setParkingZone(updateDTO.getParkingZone());
        assetParking.setParkingType(updateDTO.getParkingType());
        assetParking.setArea(updateDTO.getArea());
        assetParking.setCurrentUser(updateDTO.getCurrentUser());
        assetParking.setPlateNo(updateDTO.getPlateNo());
        assetParking.setRentPrice(updateDTO.getRentPrice());
        assetParking.setContractNo(updateDTO.getContractNo());

        return assetParkingMapper.updateAssetParking(assetParking);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAssetParkingById(Long id) {
        // Soft delete base asset
        Asset asset = assetMapper.selectById(id);
        if (asset != null) {
            asset.setDelFlag("2");
            assetMapper.updateById(asset);
        }
        // Delete parking extension
        return assetParkingMapper.deleteAssetParkingById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAssetParkingByIds(Long[] ids) {
        // Soft delete base assets
        for (Long id : ids) {
            Asset asset = assetMapper.selectById(id);
            if (asset != null) {
                asset.setDelFlag("2");
                assetMapper.updateById(asset);
            }
        }
        // Delete parking extensions
        return assetParkingMapper.deleteAssetParkingByIds(ids);
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
    public ParkingStatisticsVO getParkingStatistics(Long projectId, String parkingZone) {
        ParkingStatisticsVO statistics = assetParkingMapper.selectParkingStatistics(projectId, parkingZone);
        if (statistics != null) {
            statistics.calculateRates();
        }
        return statistics;
    }
}
