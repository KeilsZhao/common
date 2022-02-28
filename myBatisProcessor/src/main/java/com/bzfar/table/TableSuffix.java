package com.bzfar.table;

import java.lang.annotation.*;

/**
 * @author Ethons
 * @date 2021-6-22 10:28
 * 从HeadContext中取拼接的尾缀
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TableSuffix {
}
