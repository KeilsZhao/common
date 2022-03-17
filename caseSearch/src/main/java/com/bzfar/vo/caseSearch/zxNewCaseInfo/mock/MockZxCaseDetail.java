package com.bzfar.vo.caseSearch.zxNewCaseInfo.mock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "mock数据执行案件详情")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MockZxCaseDetail {

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

    @ApiModelProperty("审判长")
    private String spz;

    @ApiModelProperty("适用程序")
    private String sycx;

    @ApiModelProperty("庭审法院")
    private String court;

    @ApiModelProperty("标的金额")
    private String bdje;

    @ApiModelProperty("履行情况")
    private String lxqk;

    @ApiModelProperty("案款发放")
    private String akff;

    @ApiModelProperty("案件类型")
    private String ajlx;

}
