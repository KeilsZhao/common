package com.bzfar.vo.caseSearch.newCaseInfo.mock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "mock数据案件详情")
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

    @ApiModelProperty("收案日期")
    private String sarq;

    @ApiModelProperty("立案日期")
    private String larq;

    @ApiModelProperty("承办部门")
    private String cbbm;

    @ApiModelProperty("承办人")
    private String cbr;

    @ApiModelProperty("审判长")
    private String spz;

    @ApiModelProperty("案件来源")
    private String ajly;

    @ApiModelProperty("标的金额")
    private String bdje;

    @ApiModelProperty("受理费")
    private String slf;

    @ApiModelProperty("是否已缴费：0已缴费 1未缴费")
    private Integer sfjn;

    @ApiModelProperty("适用程序")
    private String sycx;

    @ApiModelProperty("开庭日期")
    private String ktrq;

    @ApiModelProperty("开庭地点")
    private String ktdd;

    @ApiModelProperty("结案日期")
    private String jarq;

    @ApiModelProperty("结案方式")
    private String jafs;

}
