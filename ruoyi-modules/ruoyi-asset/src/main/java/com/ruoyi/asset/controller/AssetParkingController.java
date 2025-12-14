package com.ruoyi.asset.controller;

import com.ruoyi.asset.domain.dto.AssetParkingCreateDTO;
import com.ruoyi.asset.domain.dto.AssetParkingUpdateDTO;
import com.ruoyi.asset.domain.vo.AssetParkingDetailVO;
import com.ruoyi.asset.domain.vo.AssetParkingListVO;
import com.ruoyi.asset.mapper.AssetParkingMapper.AssetParkingQuery;
import com.ruoyi.asset.service.IAssetParkingService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.domain.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.annotation.Log.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Parking Asset Controller
 */
@Api(tags = "Parking Asset Management")
@RestController
@RequestMapping("/asset/parking")
public class AssetParkingController extends BaseController {

    @Autowired
    private IAssetParkingService assetParkingService;

    /**
     * Query parking asset list
     */
    @ApiOperation("Query parking asset list")
    @PreAuthorize("@ss.hasPermi('asset:parking:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssetParkingQuery query) {
        startPage();
        List<AssetParkingListVO> list = assetParkingService.selectAssetParkingList(query);
        return getDataTable(list);
    }

    /**
     * Export parking asset list
     */
    @ApiOperation("Export parking asset list")
    @PreAuthorize("@ss.hasPermi('asset:parking:export')")
    @Log(title = "Parking Asset", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(AssetParkingQuery query) {
        List<AssetParkingListVO> list = assetParkingService.selectAssetParkingList(query);
        // TODO: Implement Excel export using ExcelUtil
        return success("Export functionality to be implemented");
    }

    /**
     * Get parking asset detail
     */
    @ApiOperation("Get parking asset detail")
    @PreAuthorize("@ss.hasPermi('asset:parking:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(
            @ApiParam(value = "Asset ID", required = true)
            @PathVariable Long id) {
        AssetParkingDetailVO detail = assetParkingService.selectAssetParkingById(id);
        return success(detail);
    }

    /**
     * Add parking asset
     */
    @ApiOperation("Add parking asset")
    @PreAuthorize("@ss.hasPermi('asset:parking:add')")
    @Log(title = "Parking Asset", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(
            @ApiParam(value = "Parking asset data", required = true)
            @Validated @RequestBody AssetParkingCreateDTO createDTO) {
        Long id = assetParkingService.insertAssetParking(createDTO);
        return success(id);
    }

    /**
     * Update parking asset
     */
    @ApiOperation("Update parking asset")
    @PreAuthorize("@ss.hasPermi('asset:parking:edit')")
    @Log(title = "Parking Asset", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(
            @ApiParam(value = "Parking asset data", required = true)
            @Validated @RequestBody AssetParkingUpdateDTO updateDTO) {
        return toAjax(assetParkingService.updateAssetParking(updateDTO));
    }

    /**
     * Delete parking assets
     */
    @ApiOperation("Delete parking assets")
    @PreAuthorize("@ss.hasPermi('asset:parking:remove')")
    @Log(title = "Parking Asset", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(
            @ApiParam(value = "Asset IDs (comma separated)", required = true)
            @PathVariable Long[] ids) {
        return toAjax(assetParkingService.deleteAssetParkingByIds(ids));
    }

    /**
     * Change parking asset status
     */
    @ApiOperation("Change parking asset status")
    @PreAuthorize("@ss.hasPermi('asset:parking:edit')")
    @Log(title = "Parking Asset", businessType = BusinessType.UPDATE)
    @PutMapping("/status/{id}")
    public AjaxResult changeStatus(
            @ApiParam(value = "Asset ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "New status code", required = true)
            @RequestParam String status,
            @ApiParam(value = "Reason for change")
            @RequestParam(required = false) String reason) {
        return toAjax(assetParkingService.changeStatus(id, status, reason));
    }
}
