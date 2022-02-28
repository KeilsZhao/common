package com.bzfar.enums;

import lombok.Getter;

/**
 * 性别枚举
 */
@Getter
public enum SexEnum {
    MALE(0 , "男"),
    FEMALE(1 , "女")
    ;

    private Integer code;

    private String msg;

    SexEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
