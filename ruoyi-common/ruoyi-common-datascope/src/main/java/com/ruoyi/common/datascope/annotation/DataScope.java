package com.ruoyi.common.datascope.annotation;

import java.lang.annotation.*;

/**
 * Data scope annotation for data permission filtering
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {
    /**
     * Department table alias
     */
    String deptAlias() default "";

    /**
     * User table alias
     */
    String userAlias() default "";

    /**
     * Permission character (for feature permission)
     */
    String permission() default "";
}
