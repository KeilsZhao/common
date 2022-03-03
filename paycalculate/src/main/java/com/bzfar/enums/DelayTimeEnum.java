package com.bzfar.enums;

import io.swagger.annotations.ApiModel;
import lombok.Getter;

/**
 * @author Administrator
 */

@ApiModel("延迟执行时间枚举")
@Getter
public enum DelayTimeEnum {
    /**
     * 年
     */
    YEAR(0),
    /**
     * 月
     */
    MONTH(1),
    /**
     * 日
     */
    DAY(2);

    /**
     * code值
     */
    private Integer code;

    DelayTimeEnum(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "0->年 1->月 2->日";
    }
}
