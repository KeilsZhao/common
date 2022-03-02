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
    YEAR(1),
    /**
     * 月
     */
    MONTH(2),
    /**
     * 日
     */
    DAY(3);

    /**
     * code值
     */
    private Integer code;

    DelayTimeEnum(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "1->年 2->月 3->日";
    }
}
