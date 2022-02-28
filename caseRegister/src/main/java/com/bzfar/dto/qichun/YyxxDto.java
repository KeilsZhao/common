package com.bzfar.dto.qichun;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@ApiModel("预约信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YyxxDto {

    @ApiModelProperty("案件类别（必填）")
    @NotBlank(message = "案件类别不能为空")
    private String nAjlb;

    @ApiModelProperty("预约信息ID")
    private String cYyxxid;

    @ApiModelProperty("适用程序")
    private String nSycx;

    @ApiModelProperty("立案案由")
    private String nLaay;

    @ApiModelProperty("起诉标的额（必填）")
    @NotBlank(message = "起诉标的额不能为空")
    private String nQsbde;

    @ApiModelProperty("收到诉状日期（必填）")
    @NotBlank(message = "收到诉状日期不能为空")
    private String dSdszrq;

    @ApiModelProperty("审判程序（必填）")
    @NotBlank(message = "审判程序不能为空")
    private String nSpcx;

    @ApiModelProperty("收案途径（必填）互联网自助终端立案 填12")
    @NotBlank(message = "收案途径不能为空")
    private String nSatj;

    @ApiModelProperty("申请人")
    private String cSqr;

    @ApiModelProperty("诉讼法院（必填）")
    @NotBlank(message = "诉讼法院不能为空")
    private String cSsfy;

    @ApiModelProperty("受理费")
    private String nSlf;

    @ApiModelProperty("申请人证件号码")
    private String cSqrzjhm;

    @ApiModelProperty("犯罪事实")
    private String cFzss;

    @ApiModelProperty("行政案件类型")
    private String nXzajlx;

    @ApiModelProperty("诉讼标的物")
    private String cSsbd;

    @ApiModelProperty("诉讼请求")
    private String cSsqq;

    @ApiModelProperty("是否公益诉讼")
    private String nSfgyss;

    @ApiModelProperty("法律依据（行政特有）")
    private String cFlyj;

    @ApiModelProperty("一审诉讼性质（刑事一审特有）")
    private String nYsssxz;

    @ApiModelProperty("起诉主罪名（刑事一审特有）")
    private String nQszzm;
}
