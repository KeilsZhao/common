package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Fimipeler
 */
@ApiModel("立案统一返回信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCaseVo {

    @ApiModelProperty("返回状态码")
    private Integer code;

    @ApiModelProperty("返回信息")
    private String msg;
}
