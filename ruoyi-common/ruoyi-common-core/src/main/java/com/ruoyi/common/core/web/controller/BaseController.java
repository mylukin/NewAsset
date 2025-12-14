package com.ruoyi.common.core.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.domain.TableDataInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Base controller with common response methods
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Start pagination
     */
    protected void startPage() {
        PageHelper.startPage(1, 10);
    }

    /**
     * Start pagination with parameters
     */
    protected void startPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
    }

    /**
     * Clear pagination
     */
    protected void clearPage() {
        PageHelper.clearPage();
    }

    /**
     * Response paginated data
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(Constants.SUCCESS);
        rspData.setMsg("Query successful");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * Response success
     */
    protected AjaxResult success() {
        return AjaxResult.success();
    }

    /**
     * Response success with data
     */
    protected AjaxResult success(Object data) {
        return AjaxResult.success(data);
    }

    /**
     * Response success with message
     */
    protected AjaxResult success(String msg) {
        return AjaxResult.success(msg);
    }

    /**
     * Response error
     */
    protected AjaxResult error() {
        return AjaxResult.error();
    }

    /**
     * Response error with message
     */
    protected AjaxResult error(String msg) {
        return AjaxResult.error(msg);
    }

    /**
     * Convert affected rows to result
     */
    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * Convert boolean result
     */
    protected AjaxResult toAjax(boolean result) {
        return result ? success() : error();
    }
}
