package com.bzfar.enums;

import io.swagger.annotations.ApiModel;
import lombok.Getter;

@ApiModel("执行计算枚举")
@Getter
public enum ExecuteEnum {

    NOMONEY(17,"非金钱给付类"),
    MONEY(18,"金钱给付类");

    /**
     * 类型ID
     */
    private Integer type;

    /**
     * 描述
     */
    private String desc;

    ExecuteEnum(Integer type,String desc){
        this.type = type;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ExecuteEnum{" +
                "type='" + type + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
