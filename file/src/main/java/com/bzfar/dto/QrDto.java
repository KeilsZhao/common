package com.bzfar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("生成二维码传递参数")
public class QrDto {

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("width")
    private Integer width;

    @ApiModelProperty("height")
    private Integer height;

    @ApiModelProperty("是否需要去掉边白，默认不去除，1去除")
    private Integer isCancle;

}
