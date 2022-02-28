package com.bzfar.table;

import java.lang.annotation.*;

/**
 * 动态表名自定义注解
 * */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TableProxy {

    String tableName() default "";
}
