package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "卷宗查询-mock数据代理人详情信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MockDlrDetail {

    @ApiModelProperty("流水号")
    private String lsh;

    @ApiModelProperty("当事人序号")
    private String dsrxh;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("律师资格证号")
    private String zjh;
}
