package com.ruoyi.common.core.page;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Table pagination data object
 */
@Data
@NoArgsConstructor
public class TableDataInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Total records */
    private long total;

    /** List data */
    private List<?> rows;

    /** Message status code */
    private int code;

    /** Message content */
    private String msg;

    /**
     * Constructor
     */
    public TableDataInfo(List<?> list, int total) {
        this.rows = list;
        this.total = total;
    }
}
