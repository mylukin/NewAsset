package com.ruoyi.common.core.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Web layer base controller
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Setup request pagination data
     */
    protected void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        String orderBy = pageDomain.getOrderBy();
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * Setup request pagination data with reasonable defaults
     */
    protected void startPageWithReasonable(Boolean reasonable) {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize).setReasonable(reasonable);
        }
    }

    /**
     * Clear pagination thread variable
     */
    protected void clearPage() {
        PageHelper.clearPage();
    }

    /**
     * Response pagination data
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(200);
        rspData.setMsg("Query successful");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * Return success
     */
    protected AjaxResult success() {
        return AjaxResult.success();
    }

    /**
     * Return success with message
     */
    protected AjaxResult success(String message) {
        return AjaxResult.success(message);
    }

    /**
     * Return success with data
     */
    protected AjaxResult success(Object data) {
        return AjaxResult.success(data);
    }

    /**
     * Return error
     */
    protected AjaxResult error() {
        return AjaxResult.error();
    }

    /**
     * Return error with message
     */
    protected AjaxResult error(String message) {
        return AjaxResult.error(message);
    }

    /**
     * Return warning message
     */
    protected AjaxResult warn(String message) {
        return AjaxResult.warn(message);
    }

    /**
     * Convert to ajax result
     */
    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * Convert to ajax result
     */
    protected AjaxResult toAjax(boolean result) {
        return result ? success() : error();
    }
}
