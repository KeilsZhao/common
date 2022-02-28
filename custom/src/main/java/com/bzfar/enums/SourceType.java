package com.bzfar.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 资源类型枚举
 */
@Getter
public enum SourceType{

    // 背景
    BACK(1,"back"),
    // icon
    ICON(2,"icon"),
    // 文字
    TEXT(3,"text"),
    // 富文本
    RICH(4,"rich"),
    // 链接
    LINK(5,"link"),
    // 英文
    ENTEXT(6,"enText"),
    // 电话
    PHONE(7,"phone"),
    // 视频
    VIDEO(8,"video"),
    // 音频
    AUDIO(9,"audio"),
    // icon1
    ICON1(10,"icon1"),
    // GIF
    GIF(11,"gif"),
    // 展示节点
    NODE(12,"node");



    @EnumValue//标记数据库存的值是code
    @JsonValue
    private final int elType;

    private String type;

    SourceType(int elType , String type){
        this.elType = elType;
        this.type = type;
    }
}
