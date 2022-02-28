package com.bzfar.annotation;


import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CalculateMethod {

    // 方法类型
    public String type();
}
