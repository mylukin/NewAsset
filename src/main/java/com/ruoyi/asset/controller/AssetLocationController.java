package com.ruoyi.asset.controller;

import com.ruoyi.asset.domain.entity.AssetLocation;
import com.ruoyi.asset.domain.vo.LocationCreateDTO;
import com.ruoyi.asset.domain.vo.LocationUpdateDTO;
import com.ruoyi.asset.service.AssetLocationService;
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
import java.util.List;
import java.util.Map;

/**
 * Asset Location Controller
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Slf4j
@RestController
@RequestMapping("/asset/location")
@Validated
@RequiredArgsConstructor
public class AssetLocationController extends BaseController {

    private final AssetLocationService assetLocationService;

    /**
     * Get location tree for project
     */
    @GetMapping("/tree")
    @PreAuthorize(hasPermi = "asset:location:list")
    public AjaxResult getTree(@RequestParam @NotNull Long projectId) {
        List<Map<String, Object>> tree = assetLocationService.getTreeByProject(projectId);
        return AjaxResult.success(tree);
    }

    /**
     * Create location
     */
    @PostMapping
    @Log(title = "Location", businessType = "ADD")
    @PreAuthorize(hasPermi = "asset:location:add")
    public AjaxResult create(@Valid @RequestBody LocationCreateDTO dto) {
        AssetLocation location = new AssetLocation();
        location.setParentId(dto.getParentId() != null ? dto.getParentId() : 0L);
        location.setProjectId(dto.getProjectId());
        location.setLevel(dto.getLevel());
        location.setCode(dto.getCode());
        location.setName(dto.getName());
        location.setSortOrder(dto.getSortOrder() != null ? dto.getSortOrder() : 0);
        location.setStatus("0");

        boolean result = assetLocationService.addLocation(location);
        return result ? AjaxResult.success("Created successfully") : AjaxResult.error("Creation failed");
    }

    /**
     * Update location
     */
    @PutMapping
    @Log(title = "Location", businessType = "EDIT")
    @PreAuthorize(hasPermi = "asset:location:edit")
    public AjaxResult update(@Valid @RequestBody LocationUpdateDTO dto) {
        AssetLocation location = new AssetLocation();
        location.setId(dto.getId());
        location.setCode(dto.getCode());
        location.setName(dto.getName());
        location.setSortOrder(dto.getSortOrder());
        if (dto.getStatus() != null) {
            location.setStatus(dto.getStatus());
        }

        boolean result = assetLocationService.updateLocation(location);
        return result ? AjaxResult.success("Updated successfully") : AjaxResult.error("Update failed");
    }

    /**
     * Delete location
     */
    @DeleteMapping("/{id}")
    @Log(title = "Location", businessType = "REMOVE")
    @PreAuthorize(hasPermi = "asset:location:remove")
    public AjaxResult delete(@PathVariable Long id) {
        try {
            boolean result = assetLocationService.deleteLocation(id);
            return result ? AjaxResult.success("Deleted successfully") : AjaxResult.error("Deletion failed");
        } catch (Exception e) {
            log.error("Failed to delete location", e);
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * Get location by ID
     */
    @GetMapping("/{id}")
    @PreAuthorize(hasPermi = "asset:location:query")
    public AjaxResult getInfo(@PathVariable Long id) {
        AssetLocation location = assetLocationService.getById(id);
        return AjaxResult.success(location);
    }

    /**
     * List locations by project
     */
    @GetMapping("/list")
    @PreAuthorize(hasPermi = "asset:location:list")
    public TableDataInfo list(@RequestParam Long projectId) {
        startPage();
        List<AssetLocation> list = assetLocationService.list(
            new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<AssetLocation>()
                .eq(AssetLocation::getProjectId, projectId)
                .orderByAsc(AssetLocation::getSortOrder)
        );
        return getDataTable(list);
    }
}
