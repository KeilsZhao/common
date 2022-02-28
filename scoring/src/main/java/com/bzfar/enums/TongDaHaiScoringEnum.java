package com.bzfar.enums;

import lombok.Getter;

/**
 * @author 刘成
 * @date 2021-6-7
 */
@Getter
public enum TongDaHaiScoringEnum {
    /**
     * 通达海副卷
     */
    VICE("副卷"),
    /**
     * 通达海正卷
     */
    POSITIVE("正卷"),
    COVER("封面"),
    TABLE("目录"),
    BACK_COVER("封底"),
    TEXT("正文")
    ;


    private String name;

    TongDaHaiScoringEnum(String name) {
        this.name = name;
    }
}
