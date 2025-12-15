package com.ruoyi.asset.controller;

import com.ruoyi.asset.domain.vo.AssetHouseCreateDTO;
import com.ruoyi.asset.domain.vo.AssetHouseUpdateDTO;
import com.ruoyi.asset.domain.vo.AssetHouseQueryDTO;
import com.ruoyi.asset.domain.vo.AssetHouseVO;
import com.ruoyi.asset.domain.vo.HouseStatsVO;
import com.ruoyi.asset.service.IAssetHouseService;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.PreAuthorize;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Asset House Controller
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Slf4j
@RestController
@RequestMapping("/asset/house")
@Validated
@RequiredArgsConstructor
public class AssetHouseController extends BaseController {

    private final IAssetHouseService assetHouseService;

    /**
     * Get house asset list
     */
    @GetMapping("/list")
    @PreAuthorize(hasPermi = "asset:house:list")
    @DataScope
    public TableDataInfo list(AssetHouseQueryDTO query) {
        startPage();
        TableDataInfo data = new TableDataInfo();
        data.setCode(200);
        data.setMsg("查询成功");

        return data;
    }

    /**
     * Get house asset detail
     */
    @GetMapping("/{id}")
    @PreAuthorize(hasPermi = "asset:house:query")
    public AjaxResult getInfo(@PathVariable @NotNull Long id) {
        AssetHouseVO vo = assetHouseService.getById(id);
        return AjaxResult.success(vo);
    }

    /**
     * Create house asset
     */
    @PostMapping
    @Log(title = "House Asset", businessType = "ADD")
    @PreAuthorize(hasPermi = "asset:house:add")
    public AjaxResult create(@Valid @RequestBody AssetHouseCreateDTO dto) {
        AssetHouseVO vo = assetHouseService.create(dto, getUserId());
        return AjaxResult.success(vo, "Created successfully");
    }

    /**
     * Update house asset
     */
    @PutMapping
    @Log(title = "House Asset", businessType = "EDIT")
    @PreAuthorize(hasPermi = "asset:house:edit")
    public AjaxResult update(@Valid @RequestBody AssetHouseUpdateDTO dto) {
        AssetHouseVO vo = assetHouseService.update(dto, getUserId());
        return AjaxResult.success(vo, "Updated successfully");
    }

    /**
     * Delete house asset
     */
    @DeleteMapping("/{ids}")
    @Log(title = "House Asset", businessType = "REMOVE")
    @PreAuthorize(hasPermi = "asset:house:remove")
    public AjaxResult delete(@PathVariable String ids) {
        try {
            String[] idArray = ids.split(",");
            Long[] longIds = new Long[idArray.length];
            for (int i = 0; i < idArray.length; i++) {
                longIds[i] = Long.parseLong(idArray[i].trim());
            }

            assetHouseService.delete(longIds, getUserId());
            return AjaxResult.success("Deleted successfully");
        } catch (Exception e) {
            log.error("Failed to delete house asset", e);
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * Copy house asset
     */
    @PostMapping("/copy/{id}")
    @Log(title = "House Asset", businessType = "ADD")
    @PreAuthorize(hasPermi = "asset:house:add")
    public AjaxResult copy(@PathVariable @NotNull Long id) {
        try {
            AssetHouseVO vo = assetHouseService.copy(id, getUserId());
            return AjaxResult.success(vo, "Copied successfully");
        } catch (Exception e) {
            log.error("Failed to copy house asset", e);
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * Export house asset list
     */
    @PostMapping("/export")
    @Log(title = "House Asset", businessType = "EXPORT")
    @PreAuthorize(hasPermi = "asset:house:export")
    public AjaxResult export(AssetHouseQueryDTO query) {
        // Implementation for export
        // This would typically generate an Excel file and return download URL
        return AjaxResult.success("Export feature to be implemented");
    }

    /**
     * Get house asset statistics
     */
    @GetMapping("/stats")
    @PreAuthorize(hasPermi = "asset:house:list")
    @DataScope
    public AjaxResult getStats(@RequestParam @NotNull Long projectId) {
        HouseStatsVO stats = assetHouseService.getStats(projectId);
        return AjaxResult.success(stats);
    }
}
