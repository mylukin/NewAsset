package com.ruoyi.common.log.annotation;

import java.lang.annotation.*;

/**
 * Custom operation log annotation
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * Module name
     */
    String title() default "";

    /**
     * Business type
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * Operator type
     */
    OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * Save request params
     */
    boolean isSaveRequestData() default true;

    /**
     * Save response data
     */
    boolean isSaveResponseData() default true;

    /**
     * Business type enumeration
     */
    enum BusinessType {
        OTHER,
        INSERT,
        UPDATE,
        DELETE,
        GRANT,
        EXPORT,
        IMPORT,
        FORCE,
        CLEAN
    }

    /**
     * Operator type enumeration
     */
    enum OperatorType {
        OTHER,
        MANAGE,
        MOBILE
    }
}
