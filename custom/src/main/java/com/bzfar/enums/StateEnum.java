package com.bzfar.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 用户状态枚举
 * @author ethons
 */
@Getter
public enum StateEnum {
    /**
     * 正常使用
     */
    NORMAL(0 , "正常使用"),
    /**
     * 删除
     */
    DELETE(1 , "删除")
    ;

    private Integer code;

    private String msg;

    StateEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
