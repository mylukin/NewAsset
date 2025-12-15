package com.ruoyi.asset.controller;

import com.ruoyi.asset.domain.vo.AssetParkingCreateDTO;
import com.ruoyi.asset.domain.vo.AssetParkingUpdateDTO;
import com.ruoyi.asset.domain.vo.AssetParkingQueryDTO;
import com.ruoyi.asset.domain.vo.AssetParkingVO;
import com.ruoyi.asset.domain.vo.ParkingStatsVO;
import com.ruoyi.asset.service.IAssetParkingService;
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
 * Asset Parking Controller
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Slf4j
@RestController
@RequestMapping("/asset/parking")
@Validated
@RequiredArgsConstructor
public class AssetParkingController extends BaseController {

    private final IAssetParkingService assetParkingService;

    /**
     * Get parking asset list
     */
    @GetMapping("/list")
    @PreAuthorize(hasPermi = "asset:parking:list")
    @DataScope
    public TableDataInfo list(AssetParkingQueryDTO query) {
        startPage();
        TableDataInfo data = new TableDataInfo();
        data.setCode(200);
        data.setMsg("查询成功");

        return data;
    }

    /**
     * Get parking asset detail
     */
    @GetMapping("/{id}")
    @PreAuthorize(hasPermi = "asset:parking:query")
    public AjaxResult getInfo(@PathVariable @NotNull Long id) {
        AssetParkingVO vo = assetParkingService.getById(id);
        return AjaxResult.success(vo);
    }

    /**
     * Create parking asset
     */
    @PostMapping
    @Log(title = "Parking Asset", businessType = "ADD")
    @PreAuthorize(hasPermi = "asset:parking:add")
    public AjaxResult create(@Valid @RequestBody AssetParkingCreateDTO dto) {
        AssetParkingVO vo = assetParkingService.create(dto, getUserId());
        return AjaxResult.success(vo, "Created successfully");
    }

    /**
     * Update parking asset
     */
    @PutMapping
    @Log(title = "Parking Asset", businessType = "EDIT")
    @PreAuthorize(hasPermi = "asset:parking:edit")
    public AjaxResult update(@Valid @RequestBody AssetParkingUpdateDTO dto) {
        AssetParkingVO vo = assetParkingService.update(dto, getUserId());
        return AjaxResult.success(vo, "Updated successfully");
    }

    /**
     * Delete parking asset
     */
    @DeleteMapping("/{ids}")
    @Log(title = "Parking Asset", businessType = "REMOVE")
    @PreAuthorize(hasPermi = "asset:parking:remove")
    public AjaxResult delete(@PathVariable String ids) {
        try {
            String[] idArray = ids.split(",");
            Long[] longIds = new Long[idArray.length];
            for (int i = 0; i < idArray.length; i++) {
                longIds[i] = Long.parseLong(idArray[i].trim());
            }

            assetParkingService.delete(longIds, getUserId());
            return AjaxResult.success("Deleted successfully");
        } catch (Exception e) {
            log.error("Failed to delete parking asset", e);
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * Export parking asset list
     */
    @PostMapping("/export")
    @Log(title = "Parking Asset", businessType = "EXPORT")
    @PreAuthorize(hasPermi = "asset:parking:export")
    public AjaxResult export(AssetParkingQueryDTO query) {
        // Implementation for export
        // This would typically generate an Excel file and return download URL
        return AjaxResult.success("Export feature to be implemented");
    }

    /**
     * Get parking asset statistics
     */
    @GetMapping("/stats")
    @PreAuthorize(hasPermi = "asset:parking:list")
    public AjaxResult getStats(@RequestParam Long projectId) {
        ParkingStatsVO stats = assetParkingService.getStats(projectId);
        return AjaxResult.success(stats);
    }
}
