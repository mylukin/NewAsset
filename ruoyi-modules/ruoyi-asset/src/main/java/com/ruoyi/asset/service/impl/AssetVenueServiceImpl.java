package com.ruoyi.asset.service.impl;

import com.ruoyi.asset.domain.dto.AssetVenueCreateDTO;
import com.ruoyi.asset.domain.dto.AssetVenueUpdateDTO;
import com.ruoyi.asset.domain.entity.Asset;
import com.ruoyi.asset.domain.entity.AssetVenue;
import com.ruoyi.asset.domain.enums.AssetStatusEnum;
import com.ruoyi.asset.domain.vo.AssetVenueDetailVO;
import com.ruoyi.asset.domain.vo.AssetVenueListVO;
import com.ruoyi.asset.domain.vo.LocationInfo;
import com.ruoyi.asset.domain.vo.VenueStatisticsVO;
import com.ruoyi.asset.mapper.AssetVenueMapper;
import com.ruoyi.asset.mapper.AssetVenueMapper.AssetVenueQuery;
import com.ruoyi.asset.mapper.AssetMapper;
import com.ruoyi.asset.service.AssetCodeGenerator;
import com.ruoyi.asset.service.IAssetVenueService;
import com.ruoyi.asset.service.rule.AssetStatusService;
import com.ruoyi.common.datascope.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Venue Asset Service Implementation
 */
@Service
public class AssetVenueServiceImpl implements IAssetVenueService {

    private static final String ASSET_TYPE_VENUE = "venue";

    @Autowired
    private AssetVenueMapper assetVenueMapper;

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private AssetCodeGenerator assetCodeGenerator;

    @Autowired
    private AssetStatusService assetStatusService;

    @Override
    @DataScope(projectAlias = "a", deptAlias = "a", userAlias = "a")
    public List<AssetVenueListVO> selectAssetVenueList(AssetVenueQuery query) {
        return assetVenueMapper.selectAssetVenueList(query);
    }

    @Override
    public AssetVenueDetailVO selectAssetVenueById(Long id) {
        return assetVenueMapper.selectAssetVenueById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertAssetVenue(AssetVenueCreateDTO createDTO) {
        // Create base asset record
        Asset asset = new Asset();
        asset.setAssetName(createDTO.getAssetName());
        asset.setAssetType(ASSET_TYPE_VENUE);
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
                ASSET_TYPE_VENUE,
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

        // Create venue extension record
        AssetVenue assetVenue = new AssetVenue();
        assetVenue.setAssetId(asset.getId());
        assetVenue.setVenueName(createDTO.getVenueName());
        assetVenue.setVenueType(createDTO.getVenueType());
        assetVenue.setArea(createDTO.getArea());
        assetVenue.setCapacity(createDTO.getCapacity());
        assetVenue.setEquipment(createDTO.getEquipment());
        assetVenue.setHourlyRate(createDTO.getHourlyRate());
        assetVenue.setDailyRate(createDTO.getDailyRate());
        assetVenue.setBookable(createDTO.getBookable());
        assetVenue.setContactPerson(createDTO.getContactPerson());
        assetVenue.setContactPhone(createDTO.getContactPhone());

        assetVenueMapper.insertAssetVenue(assetVenue);

        return asset.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateAssetVenue(AssetVenueUpdateDTO updateDTO) {
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

        // Update venue extension record
        AssetVenue assetVenue = new AssetVenue();
        assetVenue.setAssetId(updateDTO.getId());
        assetVenue.setVenueName(updateDTO.getVenueName());
        assetVenue.setVenueType(updateDTO.getVenueType());
        assetVenue.setArea(updateDTO.getArea());
        assetVenue.setCapacity(updateDTO.getCapacity());
        assetVenue.setEquipment(updateDTO.getEquipment());
        assetVenue.setHourlyRate(updateDTO.getHourlyRate());
        assetVenue.setDailyRate(updateDTO.getDailyRate());
        assetVenue.setBookable(updateDTO.getBookable());
        assetVenue.setContactPerson(updateDTO.getContactPerson());
        assetVenue.setContactPhone(updateDTO.getContactPhone());

        return assetVenueMapper.updateAssetVenue(assetVenue);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAssetVenueById(Long id) {
        // Soft delete base asset
        Asset asset = assetMapper.selectById(id);
        if (asset != null) {
            asset.setDelFlag("2");
            assetMapper.updateById(asset);
        }
        // Delete venue extension
        return assetVenueMapper.deleteAssetVenueById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAssetVenueByIds(Long[] ids) {
        // Soft delete base assets
        for (Long id : ids) {
            Asset asset = assetMapper.selectById(id);
            if (asset != null) {
                asset.setDelFlag("2");
                assetMapper.updateById(asset);
            }
        }
        // Delete venue extensions
        return assetVenueMapper.deleteAssetVenueByIds(ids);
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
    public VenueStatisticsVO getVenueStatistics(Long projectId, String venueType) {
        VenueStatisticsVO statistics = assetVenueMapper.selectVenueStatistics(projectId, venueType);
        if (statistics != null) {
            statistics.calculateRates();
        }
        return statistics;
    }
}
