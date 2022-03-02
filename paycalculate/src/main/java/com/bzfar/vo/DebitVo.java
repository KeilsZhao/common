package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel("借贷计算器返回信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DebitVo {

    @ApiModelProperty("借贷利息")
    private String debit;

}
