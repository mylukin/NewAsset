package com.ruoyi.asset.controller;

import com.ruoyi.asset.domain.dto.AssetHouseCreateDTO;
import com.ruoyi.asset.domain.dto.AssetHouseUpdateDTO;
import com.ruoyi.asset.domain.vo.AssetHouseDetailVO;
import com.ruoyi.asset.domain.vo.AssetHouseListVO;
import com.ruoyi.asset.domain.vo.HouseStatisticsVO;
import com.ruoyi.asset.mapper.AssetHouseMapper.AssetHouseQuery;
import com.ruoyi.asset.service.IAssetHouseService;
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
 * House Asset Controller
 */
@Api(tags = "House Asset Management")
@RestController
@RequestMapping("/asset/house")
public class AssetHouseController extends BaseController {

    @Autowired
    private IAssetHouseService assetHouseService;

    /**
     * Query house asset list
     */
    @ApiOperation("Query house asset list")
    @PreAuthorize("@ss.hasPermi('asset:house:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssetHouseQuery query) {
        startPage();
        List<AssetHouseListVO> list = assetHouseService.selectAssetHouseList(query);
        return getDataTable(list);
    }

    /**
     * Export house asset list
     */
    @ApiOperation("Export house asset list")
    @PreAuthorize("@ss.hasPermi('asset:house:export')")
    @Log(title = "House Asset", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(AssetHouseQuery query) {
        List<AssetHouseListVO> list = assetHouseService.selectAssetHouseList(query);
        // TODO: Implement Excel export using ExcelUtil
        return success("Export functionality to be implemented");
    }

    /**
     * Get house asset detail
     */
    @ApiOperation("Get house asset detail")
    @PreAuthorize("@ss.hasPermi('asset:house:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(
            @ApiParam(value = "Asset ID", required = true)
            @PathVariable Long id) {
        AssetHouseDetailVO detail = assetHouseService.selectAssetHouseById(id);
        return success(detail);
    }

    /**
     * Add house asset
     */
    @ApiOperation("Add house asset")
    @PreAuthorize("@ss.hasPermi('asset:house:add')")
    @Log(title = "House Asset", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(
            @ApiParam(value = "House asset data", required = true)
            @Validated @RequestBody AssetHouseCreateDTO createDTO) {
        Long id = assetHouseService.insertAssetHouse(createDTO);
        return success(id);
    }

    /**
     * Update house asset
     */
    @ApiOperation("Update house asset")
    @PreAuthorize("@ss.hasPermi('asset:house:edit')")
    @Log(title = "House Asset", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(
            @ApiParam(value = "House asset data", required = true)
            @Validated @RequestBody AssetHouseUpdateDTO updateDTO) {
        return toAjax(assetHouseService.updateAssetHouse(updateDTO));
    }

    /**
     * Delete house assets
     */
    @ApiOperation("Delete house assets")
    @PreAuthorize("@ss.hasPermi('asset:house:remove')")
    @Log(title = "House Asset", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(
            @ApiParam(value = "Asset IDs (comma separated)", required = true)
            @PathVariable Long[] ids) {
        return toAjax(assetHouseService.deleteAssetHouseByIds(ids));
    }

    /**
     * Change house asset status
     */
    @ApiOperation("Change house asset status")
    @PreAuthorize("@ss.hasPermi('asset:house:edit')")
    @Log(title = "House Asset", businessType = BusinessType.UPDATE)
    @PutMapping("/status/{id}")
    public AjaxResult changeStatus(
            @ApiParam(value = "Asset ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "New status code", required = true)
            @RequestParam String status,
            @ApiParam(value = "Reason for change")
            @RequestParam(required = false) String reason) {
        return toAjax(assetHouseService.changeStatus(id, status, reason));
    }

    /**
     * Get house asset statistics
     */
    @ApiOperation("Get house asset statistics")
    @PreAuthorize("@ss.hasPermi('asset:house:list')")
    @GetMapping("/statistics")
    public AjaxResult getStatistics(
            @ApiParam(value = "Project ID filter (optional)")
            @RequestParam(required = false) Long projectId) {
        HouseStatisticsVO statistics = assetHouseService.getHouseStatistics(projectId);
        return success(statistics);
    }
}
