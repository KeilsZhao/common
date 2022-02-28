package com.bzfar.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 项目类型枚举
 */
@Getter
public enum ProjectType {

    // 横屏
    BACK(0,"landscape"),
    // 竖屏
    ICON(1,"portrait");



    @EnumValue//标记数据库存的值是code
    @JsonValue
    private final int type;

    private String typeDes;

    ProjectType(int type , String typeDes){
        this.type = type;
        this.typeDes = typeDes;
    }
}
