package com.ruoyi.asset.controller;

import com.ruoyi.asset.domain.dto.AssetFacilityCreateDTO;
import com.ruoyi.asset.domain.dto.AssetFacilityUpdateDTO;
import com.ruoyi.asset.domain.vo.AssetFacilityDetailVO;
import com.ruoyi.asset.domain.vo.AssetFacilityListVO;
import com.ruoyi.asset.domain.vo.FacilityWarrantyExpiringVO;
import com.ruoyi.asset.mapper.AssetFacilityMapper.AssetFacilityQuery;
import com.ruoyi.asset.service.IAssetFacilityService;
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
 * Facility Asset Controller
 */
@Api(tags = "Facility Asset Management")
@RestController
@RequestMapping("/asset/facility")
public class AssetFacilityController extends BaseController {

    @Autowired
    private IAssetFacilityService assetFacilityService;

    /**
     * Query facility asset list
     */
    @ApiOperation("Query facility asset list")
    @PreAuthorize("@ss.hasPermi('asset:facility:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssetFacilityQuery query) {
        startPage();
        List<AssetFacilityListVO> list = assetFacilityService.selectAssetFacilityList(query);
        return getDataTable(list);
    }

    /**
     * Export facility asset list
     */
    @ApiOperation("Export facility asset list")
    @PreAuthorize("@ss.hasPermi('asset:facility:export')")
    @Log(title = "Facility Asset", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(AssetFacilityQuery query) {
        List<AssetFacilityListVO> list = assetFacilityService.selectAssetFacilityList(query);
        // TODO: Implement Excel export using ExcelUtil
        return success("Export functionality to be implemented");
    }

    /**
     * Get facility asset detail
     */
    @ApiOperation("Get facility asset detail")
    @PreAuthorize("@ss.hasPermi('asset:facility:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(
            @ApiParam(value = "Asset ID", required = true)
            @PathVariable Long id) {
        AssetFacilityDetailVO detail = assetFacilityService.selectAssetFacilityById(id);
        return success(detail);
    }

    /**
     * Add facility asset
     */
    @ApiOperation("Add facility asset")
    @PreAuthorize("@ss.hasPermi('asset:facility:add')")
    @Log(title = "Facility Asset", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(
            @ApiParam(value = "Facility asset data", required = true)
            @Validated @RequestBody AssetFacilityCreateDTO createDTO) {
        Long id = assetFacilityService.insertAssetFacility(createDTO);
        return success(id);
    }

    /**
     * Update facility asset
     */
    @ApiOperation("Update facility asset")
    @PreAuthorize("@ss.hasPermi('asset:facility:edit')")
    @Log(title = "Facility Asset", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(
            @ApiParam(value = "Facility asset data", required = true)
            @Validated @RequestBody AssetFacilityUpdateDTO updateDTO) {
        return toAjax(assetFacilityService.updateAssetFacility(updateDTO));
    }

    /**
     * Delete facility assets
     */
    @ApiOperation("Delete facility assets")
    @PreAuthorize("@ss.hasPermi('asset:facility:remove')")
    @Log(title = "Facility Asset", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(
            @ApiParam(value = "Asset IDs (comma separated)", required = true)
            @PathVariable Long[] ids) {
        return toAjax(assetFacilityService.deleteAssetFacilityByIds(ids));
    }

    /**
     * Change facility asset status
     */
    @ApiOperation("Change facility asset status")
    @PreAuthorize("@ss.hasPermi('asset:facility:edit')")
    @Log(title = "Facility Asset", businessType = BusinessType.UPDATE)
    @PutMapping("/status/{id}")
    public AjaxResult changeStatus(
            @ApiParam(value = "Asset ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "New status code", required = true)
            @RequestParam String status,
            @ApiParam(value = "Reason for change")
            @RequestParam(required = false) String reason) {
        return toAjax(assetFacilityService.changeStatus(id, status, reason));
    }

    /**
     * Get facilities with warranty expiring within specified days
     */
    @ApiOperation("Get facilities with expiring warranty")
    @PreAuthorize("@ss.hasPermi('asset:facility:list')")
    @GetMapping("/expiring")
    public AjaxResult getExpiringFacilities(
            @ApiParam(value = "Days ahead to check", defaultValue = "30")
            @RequestParam(defaultValue = "30") int daysAhead,
            @ApiParam(value = "Project ID filter")
            @RequestParam(required = false) Long projectId) {
        List<FacilityWarrantyExpiringVO> list = assetFacilityService.getExpiringFacilities(daysAhead, projectId);
        return success(list);
    }
}
