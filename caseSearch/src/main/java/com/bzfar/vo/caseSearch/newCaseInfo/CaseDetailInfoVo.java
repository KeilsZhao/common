package com.bzfar.vo.caseSearch.newCaseInfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
@ApiModel(value = "案件详情信息" , description = "案件详情信息，案件详情页面使用")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CaseDetailInfoVo {

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
    private Integer isPay;

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

    @ApiModelProperty("原告")
    private List<CaseDsrInfoVo> yg;

    @ApiModelProperty("被告")
    private List<CaseDsrInfoVo> bg;
}
