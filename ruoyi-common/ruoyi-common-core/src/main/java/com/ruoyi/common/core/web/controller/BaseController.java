package com.ruoyi.common.core.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.domain.TableDataInfo;

import java.util.List;

/**
 * Base controller class
 */
public class BaseController {

    /**
     * Set request pagination data
     */
    protected void startPage() {
        PageHelper.startPage(1, 10);
    }

    /**
     * Response request pagination data
     */
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(200);
        rspData.setMsg("Success");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * Return success response
     */
    protected AjaxResult success() {
        return AjaxResult.success();
    }

    /**
     * Return success response with data
     */
    protected AjaxResult success(Object data) {
        return AjaxResult.success(data);
    }

    /**
     * Return success response with message
     */
    protected AjaxResult success(String message) {
        return AjaxResult.success(message);
    }

    /**
     * Return success response with message and data
     */
    protected AjaxResult success(String message, Object data) {
        return AjaxResult.success(message, data);
    }

    /**
     * Return error response
     */
    protected AjaxResult error() {
        return AjaxResult.error();
    }

    /**
     * Return error response with message
     */
    protected AjaxResult error(String message) {
        return AjaxResult.error(message);
    }

    /**
     * Return error response with code and message
     */
    protected AjaxResult error(int code, String message) {
        return AjaxResult.error(code, message);
    }

    /**
     * Return AJAX result based on boolean
     */
    protected AjaxResult toAjax(boolean result) {
        return result ? success() : error();
    }

    /**
     * Return AJAX result based on affected rows
     */
    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? success() : error();
    }
}
