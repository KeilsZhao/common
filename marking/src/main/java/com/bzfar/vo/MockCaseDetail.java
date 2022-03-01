package com.bzfar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "卷宗查询-mock数据案件详情")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MockCaseDetail {

    @ApiModelProperty("流水号")
    private String lsh;

    @ApiModelProperty("代字")
    private String dz;

    @ApiModelProperty("案号")
    private String ah;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("案由")
    private String ay;

    @ApiModelProperty("立案日期")
    private String larq;

    @ApiModelProperty("结案日期")
    private String jarq;

    @ApiModelProperty("承办部门")
    private String cbbm;

    @ApiModelProperty("承办人")
    private String cbr;

    @ApiModelProperty("适用程序")
    private String sycx;

}
