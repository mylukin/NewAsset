package com.ruoyi.common.core.web.domain;

import com.ruoyi.common.core.constant.Constants;

import java.util.HashMap;

/**
 * Ajax result wrapper
 */
public class AjaxResult extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public static final String CODE_TAG = "code";
    public static final String MSG_TAG = "msg";
    public static final String DATA_TAG = "data";

    public AjaxResult() {
    }

    public AjaxResult(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    public AjaxResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (data != null) {
            super.put(DATA_TAG, data);
        }
    }

    public static AjaxResult success() {
        return AjaxResult.success("Success");
    }

    public static AjaxResult success(Object data) {
        return AjaxResult.success("Success", data);
    }

    public static AjaxResult success(String msg) {
        return AjaxResult.success(msg, null);
    }

    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(Constants.SUCCESS, msg, data);
    }

    public static AjaxResult error() {
        return AjaxResult.error("Operation failed");
    }

    public static AjaxResult error(String msg) {
        return AjaxResult.error(msg, null);
    }

    public static AjaxResult error(String msg, Object data) {
        return new AjaxResult(Constants.FAIL, msg, data);
    }

    public static AjaxResult error(int code, String msg) {
        return new AjaxResult(code, msg, null);
    }

    @Override
    public AjaxResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
