package com.bzfar.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("裁判文书列表获取参数（案由或法院名称）")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CpwsDto {

    @ApiModelProperty("条件")
    private String condition;

    @ApiModelProperty("显示条数")
    private Integer num;

}
