package com.bzfar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@ApiModel("资源")
@AllArgsConstructor
@NoArgsConstructor
public class ComSourceVo {

    @ApiModelProperty("资源ID")
    private String sourceId;

    @ApiModelProperty("资源类型")
    private String elType;

    @ApiModelProperty("资源值")
    private String value;

    @ApiModelProperty("是否展示")
    private String isShow;
}
