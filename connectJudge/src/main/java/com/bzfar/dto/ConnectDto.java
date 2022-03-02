package com.bzfar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Administrator
 */
@ApiModel("约见法官信息提交参数")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConnectDto {

    @ApiModelProperty("案件流水号")
    private String lsh;

    @ApiModelProperty("输入的手机号")
    private String phone;

    @ApiModelProperty("内容")
    private String content;
}
