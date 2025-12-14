package com.ruoyi.asset.controller;

import com.ruoyi.asset.domain.dto.AssetVenueCreateDTO;
import com.ruoyi.asset.domain.dto.AssetVenueUpdateDTO;
import com.ruoyi.asset.domain.vo.AssetVenueDetailVO;
import com.ruoyi.asset.domain.vo.AssetVenueListVO;
import com.ruoyi.asset.domain.vo.VenueStatisticsVO;
import com.ruoyi.asset.mapper.AssetVenueMapper.AssetVenueQuery;
import com.ruoyi.asset.service.IAssetVenueService;
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
 * Venue Asset Controller
 */
@Api(tags = "Venue Asset Management")
@RestController
@RequestMapping("/asset/venue")
public class AssetVenueController extends BaseController {

    @Autowired
    private IAssetVenueService assetVenueService;

    /**
     * Query venue asset list
     */
    @ApiOperation("Query venue asset list")
    @PreAuthorize("@ss.hasPermi('asset:venue:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssetVenueQuery query) {
        startPage();
        List<AssetVenueListVO> list = assetVenueService.selectAssetVenueList(query);
        return getDataTable(list);
    }

    /**
     * Export venue asset list
     */
    @ApiOperation("Export venue asset list")
    @PreAuthorize("@ss.hasPermi('asset:venue:export')")
    @Log(title = "Venue Asset", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(AssetVenueQuery query) {
        List<AssetVenueListVO> list = assetVenueService.selectAssetVenueList(query);
        // TODO: Implement Excel export using ExcelUtil
        return success("Export functionality to be implemented");
    }

    /**
     * Get venue asset detail
     */
    @ApiOperation("Get venue asset detail")
    @PreAuthorize("@ss.hasPermi('asset:venue:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(
            @ApiParam(value = "Asset ID", required = true)
            @PathVariable Long id) {
        AssetVenueDetailVO detail = assetVenueService.selectAssetVenueById(id);
        return success(detail);
    }

    /**
     * Add venue asset
     */
    @ApiOperation("Add venue asset")
    @PreAuthorize("@ss.hasPermi('asset:venue:add')")
    @Log(title = "Venue Asset", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(
            @ApiParam(value = "Venue asset data", required = true)
            @Validated @RequestBody AssetVenueCreateDTO createDTO) {
        Long id = assetVenueService.insertAssetVenue(createDTO);
        return success(id);
    }

    /**
     * Update venue asset
     */
    @ApiOperation("Update venue asset")
    @PreAuthorize("@ss.hasPermi('asset:venue:edit')")
    @Log(title = "Venue Asset", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(
            @ApiParam(value = "Venue asset data", required = true)
            @Validated @RequestBody AssetVenueUpdateDTO updateDTO) {
        return toAjax(assetVenueService.updateAssetVenue(updateDTO));
    }

    /**
     * Delete venue assets
     */
    @ApiOperation("Delete venue assets")
    @PreAuthorize("@ss.hasPermi('asset:venue:remove')")
    @Log(title = "Venue Asset", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(
            @ApiParam(value = "Asset IDs (comma separated)", required = true)
            @PathVariable Long[] ids) {
        return toAjax(assetVenueService.deleteAssetVenueByIds(ids));
    }

    /**
     * Change venue asset status
     */
    @ApiOperation("Change venue asset status")
    @PreAuthorize("@ss.hasPermi('asset:venue:edit')")
    @Log(title = "Venue Asset", businessType = BusinessType.UPDATE)
    @PutMapping("/status/{id}")
    public AjaxResult changeStatus(
            @ApiParam(value = "Asset ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "New status code", required = true)
            @RequestParam String status,
            @ApiParam(value = "Reason for change")
            @RequestParam(required = false) String reason) {
        return toAjax(assetVenueService.changeStatus(id, status, reason));
    }

    /**
     * Get venue asset statistics
     */
    @ApiOperation("Get venue asset statistics")
    @PreAuthorize("@ss.hasPermi('asset:venue:list')")
    @GetMapping("/statistics")
    public AjaxResult getStatistics(
            @ApiParam(value = "Project ID filter")
            @RequestParam(required = false) Long projectId,
            @ApiParam(value = "Venue type filter")
            @RequestParam(required = false) String venueType) {
        VenueStatisticsVO statistics = assetVenueService.getVenueStatistics(projectId, venueType);
        return success(statistics);
    }
}
