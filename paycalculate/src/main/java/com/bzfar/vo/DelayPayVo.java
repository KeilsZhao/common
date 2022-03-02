package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel("延迟执行费用返回")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DelayPayVo {

    @ApiModelProperty("一般债务利息")
    private String common;

    @ApiModelProperty("加倍部分债务利息")
    private String twice;

    @ApiModelProperty("延迟期间债务利息")
    private String delay;

}
