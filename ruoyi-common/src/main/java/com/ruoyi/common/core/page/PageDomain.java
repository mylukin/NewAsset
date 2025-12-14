package com.ruoyi.common.core.page;

import com.ruoyi.common.utils.StringUtils;
import lombok.Data;

/**
 * Pagination data
 */
@Data
public class PageDomain {
    /** Current page number */
    private Integer pageNum;

    /** Number of records per page */
    private Integer pageSize;

    /** Sort column */
    private String orderByColumn;

    /** Sort direction (desc/asc) */
    private String isAsc = "asc";

    /** Paging parameter reasonable, default true on page < 1 will query first page, exceeding total pages will query last page */
    private Boolean reasonable = true;

    public String getOrderBy() {
        if (StringUtils.isEmpty(orderByColumn)) {
            return "";
        }
        return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
    }

    public String getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(String isAsc) {
        if (StringUtils.isNotEmpty(isAsc)) {
            if ("ascending".equals(isAsc)) {
                isAsc = "asc";
            } else if ("descending".equals(isAsc)) {
                isAsc = "desc";
            }
            this.isAsc = isAsc;
        }
    }
}
