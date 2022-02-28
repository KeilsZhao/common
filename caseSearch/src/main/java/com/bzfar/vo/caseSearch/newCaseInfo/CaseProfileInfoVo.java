package com.bzfar.vo.caseSearch.newCaseInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @author Administrator
 */
@ApiModel(value = "案件概要信息",description = "根据ui图所构造返回内容,用于当律师多个案件时，需进行当事人的身份校验使用")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CaseProfileInfoVo {

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

    @ApiModelProperty("原告名称")
    private List<String> yg;

    @ApiModelProperty("被告名称")
    private List<String> bg;

}
