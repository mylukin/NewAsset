package com.ruoyi.common.exception;

/**
 * Business exception
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /** Error code */
    private Integer code;

    /** Error message */
    private String message;

    /** Error detail message (not shown to user) */
    private String detailMessage;

    /**
     * Empty constructor for easy override
     */
    public ServiceException() {
    }

    public ServiceException(String message) {
        this.message = message;
    }

    public ServiceException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public ServiceException setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ServiceException setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getCode() {
        return code;
    }
}
