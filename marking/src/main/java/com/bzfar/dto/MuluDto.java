package com.bzfar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("目录参数")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MuluDto {

    @ApiModelProperty(value = "案号")
    private String ah;

    @ApiModelProperty(value = "用户id")
    private String userid;

    @ApiModelProperty(value = "密码")
    private String pwd;

}
