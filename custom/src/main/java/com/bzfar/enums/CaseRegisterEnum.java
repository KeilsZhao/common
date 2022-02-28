package com.bzfar.enums;

import lombok.Getter;

/**
 * 立案枚举
 *
 * @author Fimipeler
 */
@Getter
public enum CaseRegisterEnum {

    /**
     * 蕲春
     */
    QICHUN("QiChun","蕲春立案");


    /**
     * code值与实现类对应，使用驼峰命名，放入代理类。如: QiChun
     */
    private String code;

    /**
     * 描述信息，如：蕲春立案
     */
    private String desc;

    CaseRegisterEnum(String code,String desc){
        this.code = code;
        this.desc = desc;
    }
}
