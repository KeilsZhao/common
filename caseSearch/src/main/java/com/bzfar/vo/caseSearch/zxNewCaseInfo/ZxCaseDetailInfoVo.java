package com.bzfar.vo.caseSearch.zxNewCaseInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @author Administrator
 */
@ApiModel(value = "执行案件详情信", description = "执行案件详情信息，案件详情页面使用")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ZxCaseDetailInfoVo {

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

    @ApiModelProperty("申请人")
    private List<ZxCaseDsrInfoVo> sqr;

    @ApiModelProperty("被执行人")
    private List<ZxCaseDsrInfoVo> bzxr;
}
