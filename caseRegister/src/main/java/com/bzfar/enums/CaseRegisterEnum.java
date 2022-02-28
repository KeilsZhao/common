package com.bzfar.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel("立案枚举")
public enum CaseRegisterEnum {

    STAND("Stand","标准版立案接口，采用四川简阳，立案接口因其特殊性，接口需根据产品时时调整"),
    QICHUN("QiChun","蕲春立案");

    @ApiModelProperty("code值与实现类对应，使用驼峰命名，放入代理类。如: QiChun")
    private String code;

    @ApiModelProperty("描述")
    private String desc;

    CaseRegisterEnum(String code,String desc){
        this.code = code;
        this.desc = desc;
    }
}
