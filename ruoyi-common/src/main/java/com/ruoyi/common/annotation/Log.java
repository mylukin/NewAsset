package com.ruoyi.common.annotation;

import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.OperatorType;

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
     * Business function type
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * Operator type
     */
    OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * Whether to save request parameters
     */
    boolean isSaveRequestData() default true;

    /**
     * Whether to save response parameters
     */
    boolean isSaveResponseData() default true;

    /**
     * Excluded parameter names
     */
    String[] excludeParamNames() default {};
}
