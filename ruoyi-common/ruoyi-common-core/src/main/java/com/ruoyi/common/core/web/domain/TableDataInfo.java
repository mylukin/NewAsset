package com.ruoyi.common.core.web.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Table data response wrapper
 */
public class TableDataInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Total records */
    private long total;

    /** List data */
    private List<?> rows;

    /** Response code */
    private int code;

    /** Response message */
    private String msg;

    public TableDataInfo() {
    }

    public TableDataInfo(List<?> list, int total) {
        this.rows = list;
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
