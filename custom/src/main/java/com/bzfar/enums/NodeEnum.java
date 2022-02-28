package com.bzfar.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 节点类型枚举
 */
@Getter
public enum NodeEnum {

    TEST(0,"预留");

    NodeEnum(int type, String typeDesc) {
        this.type = type;
        this.typeDesc = typeDesc;
    }

    @EnumValue//标记数据库存的值是code
    @JsonValue
    private final int type;

    private String typeDesc;


}
