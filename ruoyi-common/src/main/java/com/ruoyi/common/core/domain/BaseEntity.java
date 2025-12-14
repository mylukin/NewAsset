package com.ruoyi.common.core.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity base class
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Search value */
    @JsonIgnore
    private String searchValue;

    /** Creator */
    private String createBy;

    /** Create time */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** Updater */
    private String updateBy;

    /** Update time */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** Remark */
    private String remark;

    /** Request params */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> params;

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }
}
