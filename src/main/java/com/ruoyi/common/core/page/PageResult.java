package com.ruoyi.common.core.page;

import lombok.Data;

import java.util.List;

/**
 * Page Result
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Data
public class PageResult<T> {

    /**
     * Current page number (1-based)
     */
    private Long current;

    /**
     * Page size
     */
    private Long size;

    /**
     * Total count
     */
    private Long total;

    /**
     * Total pages
     */
    private Long pages;

    /**
     * Data list
     */
    private List<T> data;

    public PageResult() {}

    public PageResult(Long current, Long size, Long total, List<T> data) {
        this.current = current;
        this.size = size;
        this.total = total;
        this.data = data;
        this.pages = (total + size - 1) / size;
    }

    public static <T> PageResult<T> of(Long current, Long size, Long total, List<T> data) {
        return new PageResult<>(current, size, total, data);
    }
}
