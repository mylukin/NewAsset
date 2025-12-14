package com.ruoyi.asset.controller;

import com.ruoyi.asset.domain.dto.AssetMaintOrderCreateDTO;
import com.ruoyi.asset.domain.vo.AssetMaintOrderDetailVO;
import com.ruoyi.asset.domain.vo.AssetMaintOrderListVO;
import com.ruoyi.asset.mapper.AssetMaintOrderMapper.MaintOrderQuery;
import com.ruoyi.asset.service.IAssetMaintOrderService;
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
 * Maintenance Order Controller
 */
@Api(tags = "Maintenance Order Management")
@RestController
@RequestMapping("/asset/maint/order")
public class AssetMaintOrderController extends BaseController {

    @Autowired
    private IAssetMaintOrderService maintOrderService;

    // ==================== CRUD Endpoints ====================

    /**
     * Query maintenance order list
     */
    @ApiOperation("Query maintenance order list")
    @PreAuthorize("@ss.hasPermi('asset:maint:list')")
    @GetMapping("/list")
    public TableDataInfo list(MaintOrderQuery query) {
        startPage();
        List<AssetMaintOrderListVO> list = maintOrderService.selectMaintOrderList(query);
        return getDataTable(list);
    }

    /**
     * Get maintenance order detail
     */
    @ApiOperation("Get maintenance order detail")
    @PreAuthorize("@ss.hasPermi('asset:maint:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(
            @ApiParam(value = "Order ID", required = true)
            @PathVariable Long id) {
        AssetMaintOrderDetailVO detail = maintOrderService.selectMaintOrderById(id);
        return success(detail);
    }

    /**
     * Create maintenance order
     */
    @ApiOperation("Create maintenance order")
    @PreAuthorize("@ss.hasPermi('asset:maint:add')")
    @Log(title = "Maintenance Order", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(
            @ApiParam(value = "Order data", required = true)
            @Validated @RequestBody AssetMaintOrderCreateDTO createDTO) {
        Long id = maintOrderService.createMaintOrder(createDTO);
        return success(id);
    }

    /**
     * Delete maintenance orders
     */
    @ApiOperation("Delete maintenance orders")
    @PreAuthorize("@ss.hasPermi('asset:maint:remove')")
    @Log(title = "Maintenance Order", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(
            @ApiParam(value = "Order IDs", required = true)
            @PathVariable Long[] ids) {
        return toAjax(maintOrderService.deleteMaintOrderByIds(ids));
    }

    // ==================== Workflow Endpoints ====================

    /**
     * Assign order to handler
     */
    @ApiOperation("Assign order to handler")
    @PreAuthorize("@ss.hasPermi('asset:maint:assign')")
    @Log(title = "Maintenance Order", businessType = BusinessType.UPDATE)
    @PutMapping("/assign/{id}")
    public AjaxResult assign(
            @ApiParam(value = "Order ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Handler user ID", required = true)
            @RequestParam Long handlerId,
            @ApiParam(value = "Remark")
            @RequestParam(required = false) String remark) {
        return toAjax(maintOrderService.assignOrder(id, handlerId, remark));
    }

    /**
     * Accept an assigned order
     */
    @ApiOperation("Accept an assigned order")
    @PreAuthorize("@ss.hasPermi('asset:maint:accept')")
    @Log(title = "Maintenance Order", businessType = BusinessType.UPDATE)
    @PutMapping("/accept/{id}")
    public AjaxResult accept(
            @ApiParam(value = "Order ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Remark")
            @RequestParam(required = false) String remark) {
        return toAjax(maintOrderService.acceptOrder(id, remark));
    }

    /**
     * Reject an assigned order
     */
    @ApiOperation("Reject an assigned order")
    @PreAuthorize("@ss.hasPermi('asset:maint:reject')")
    @Log(title = "Maintenance Order", businessType = BusinessType.UPDATE)
    @PutMapping("/reject/{id}")
    public AjaxResult reject(
            @ApiParam(value = "Order ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Rejection reason", required = true)
            @RequestParam String reason) {
        return toAjax(maintOrderService.rejectOrder(id, reason));
    }

    /**
     * Complete maintenance work
     */
    @ApiOperation("Complete maintenance work")
    @PreAuthorize("@ss.hasPermi('asset:maint:complete')")
    @Log(title = "Maintenance Order", businessType = BusinessType.UPDATE)
    @PutMapping("/complete/{id}")
    public AjaxResult complete(
            @ApiParam(value = "Order ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Completion result")
            @RequestParam(required = false) String result) {
        return toAjax(maintOrderService.completeOrder(id, result));
    }

    /**
     * Confirm and close order
     */
    @ApiOperation("Confirm and close order")
    @PreAuthorize("@ss.hasPermi('asset:maint:confirm')")
    @Log(title = "Maintenance Order", businessType = BusinessType.UPDATE)
    @PutMapping("/confirm/{id}")
    public AjaxResult confirm(
            @ApiParam(value = "Order ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Feedback")
            @RequestParam(required = false) String feedback) {
        return toAjax(maintOrderService.confirmAndClose(id, feedback));
    }

    /**
     * Cancel order
     */
    @ApiOperation("Cancel order")
    @PreAuthorize("@ss.hasPermi('asset:maint:cancel')")
    @Log(title = "Maintenance Order", businessType = BusinessType.UPDATE)
    @PutMapping("/cancel/{id}")
    public AjaxResult cancel(
            @ApiParam(value = "Order ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Cancellation reason", required = true)
            @RequestParam String reason) {
        return toAjax(maintOrderService.cancelOrder(id, reason));
    }

    // ==================== Query Endpoints ====================

    /**
     * Count orders by status
     */
    @ApiOperation("Count orders by status")
    @PreAuthorize("@ss.hasPermi('asset:maint:list')")
    @GetMapping("/count")
    public AjaxResult countByStatus(
            @ApiParam(value = "Project ID")
            @RequestParam(required = false) Long projectId,
            @ApiParam(value = "Status code", required = true)
            @RequestParam String status) {
        return success(maintOrderService.countByStatus(projectId, status));
    }

    /**
     * Get orders for a specific asset
     */
    @ApiOperation("Get orders for a specific asset")
    @PreAuthorize("@ss.hasPermi('asset:maint:list')")
    @GetMapping("/asset/{assetId}")
    public AjaxResult getByAssetId(
            @ApiParam(value = "Asset ID", required = true)
            @PathVariable Long assetId) {
        return success(maintOrderService.getOrdersByAssetId(assetId));
    }

    /**
     * Export maintenance order list
     */
    @ApiOperation("Export maintenance order list")
    @PreAuthorize("@ss.hasPermi('asset:maint:export')")
    @Log(title = "Maintenance Order", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(MaintOrderQuery query) {
        List<AssetMaintOrderListVO> list = maintOrderService.selectMaintOrderList(query);
        // TODO: Implement Excel export using ExcelUtil
        return success("Export functionality to be implemented");
    }
}
