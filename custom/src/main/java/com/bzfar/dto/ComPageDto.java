package com.bzfar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@ApiModel("待处理资源")
@Data
@Builder
public class ComPageDto {

    @ApiModelProperty("待处理节点信息")
    private List<ComNodeDto> nodes;

    @ApiModelProperty("待处理节点资源信息")
    private List<ComNodeSourceVo> nodeSource;

    @ApiModelProperty("待处理资源")
    private List<ComSourceVo> source;

    @ApiModelProperty("资源路径")
    private String url;
}
