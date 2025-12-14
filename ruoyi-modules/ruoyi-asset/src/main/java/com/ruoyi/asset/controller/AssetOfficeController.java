package com.ruoyi.asset.controller;

import com.ruoyi.asset.domain.dto.AssetOfficeCreateDTO;
import com.ruoyi.asset.domain.dto.AssetOfficeUpdateDTO;
import com.ruoyi.asset.domain.vo.AssetOfficeDetailVO;
import com.ruoyi.asset.domain.vo.AssetOfficeListVO;
import com.ruoyi.asset.mapper.AssetOfficeMapper.AssetOfficeQuery;
import com.ruoyi.asset.service.IAssetOfficeService;
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
 * Office Asset Controller
 */
@Api(tags = "Office Asset Management")
@RestController
@RequestMapping("/asset/office")
public class AssetOfficeController extends BaseController {

    @Autowired
    private IAssetOfficeService assetOfficeService;

    /**
     * Query office asset list
     */
    @ApiOperation("Query office asset list")
    @PreAuthorize("@ss.hasPermi('asset:office:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssetOfficeQuery query) {
        startPage();
        List<AssetOfficeListVO> list = assetOfficeService.selectAssetOfficeList(query);
        return getDataTable(list);
    }

    /**
     * Export office asset list
     */
    @ApiOperation("Export office asset list")
    @PreAuthorize("@ss.hasPermi('asset:office:export')")
    @Log(title = "Office Asset", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(AssetOfficeQuery query) {
        List<AssetOfficeListVO> list = assetOfficeService.selectAssetOfficeList(query);
        // TODO: Implement Excel export using ExcelUtil
        return success("Export functionality to be implemented");
    }

    /**
     * Get office asset detail
     */
    @ApiOperation("Get office asset detail")
    @PreAuthorize("@ss.hasPermi('asset:office:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(
            @ApiParam(value = "Asset ID", required = true)
            @PathVariable Long id) {
        AssetOfficeDetailVO detail = assetOfficeService.selectAssetOfficeById(id);
        return success(detail);
    }

    /**
     * Add office asset
     */
    @ApiOperation("Add office asset")
    @PreAuthorize("@ss.hasPermi('asset:office:add')")
    @Log(title = "Office Asset", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(
            @ApiParam(value = "Office asset data", required = true)
            @Validated @RequestBody AssetOfficeCreateDTO createDTO) {
        Long id = assetOfficeService.insertAssetOffice(createDTO);
        return success(id);
    }

    /**
     * Update office asset
     */
    @ApiOperation("Update office asset")
    @PreAuthorize("@ss.hasPermi('asset:office:edit')")
    @Log(title = "Office Asset", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(
            @ApiParam(value = "Office asset data", required = true)
            @Validated @RequestBody AssetOfficeUpdateDTO updateDTO) {
        return toAjax(assetOfficeService.updateAssetOffice(updateDTO));
    }

    /**
     * Delete office assets
     */
    @ApiOperation("Delete office assets")
    @PreAuthorize("@ss.hasPermi('asset:office:remove')")
    @Log(title = "Office Asset", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(
            @ApiParam(value = "Asset IDs (comma separated)", required = true)
            @PathVariable Long[] ids) {
        return toAjax(assetOfficeService.deleteAssetOfficeByIds(ids));
    }

    /**
     * Change office asset status
     */
    @ApiOperation("Change office asset status")
    @PreAuthorize("@ss.hasPermi('asset:office:edit')")
    @Log(title = "Office Asset", businessType = BusinessType.UPDATE)
    @PutMapping("/status/{id}")
    public AjaxResult changeStatus(
            @ApiParam(value = "Asset ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "New status code", required = true)
            @RequestParam String status,
            @ApiParam(value = "Reason for change")
            @RequestParam(required = false) String reason) {
        return toAjax(assetOfficeService.changeStatus(id, status, reason));
    }
}
