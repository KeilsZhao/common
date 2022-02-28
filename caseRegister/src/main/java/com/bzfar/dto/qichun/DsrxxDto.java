package com.bzfar.dto.qichun;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author Fimipeler
 */
@ApiModel("当事人信息")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DsrxxDto {

    @ApiModelProperty("预约信息ID，（必填）")
    private String cYyxxid;

    @ApiModelProperty("当事人唯一标识（必填）")
    private String cDsrWybs;

    @ApiModelProperty("联系电话，练习方式类型为移动电话时（被告非必填）")
    private String cLxdh;

    @ApiModelProperty("联系方式类型（被告非必填）")
    private String nLxfslx;

    @ApiModelProperty("其他联系方法，联系方式为非移动电话（被告非必填）")
    private String cLxff;

    @ApiModelProperty("现住地址（被告非必填）")
    private String cXzdz;

    @ApiModelProperty("身份证号码")
    private String cSfzhm;

    @ApiModelProperty("出生日期")
    private String dCsrq;

    @ApiModelProperty("当事人名称（必填）")
    @NotBlank(message = "当事人名称不能为空")
    private String cMc;

    @ApiModelProperty("名族")
    private String nMz;

    @ApiModelProperty("国籍")
    private String nGj;

    @ApiModelProperty("户籍地址")
    private String cHjdz;

    @ApiModelProperty("住所")
    private String cZybsjgszd;

    @ApiModelProperty("身份证件种类")
    private String nSfzjzl;

    @ApiModelProperty("当事人类型（必填）")
    @NotBlank(message = "当事人类型不能为空")
    private String nDsrlx;

    @ApiModelProperty("诉讼地位（必填）")
    @NotBlank(message = "诉讼地位不能为空")
    private String nSsdw;

    @ApiModelProperty("性别（被告非必填）")
    private String nXb;

    @ApiModelProperty("单位地址 np中为地址（被告非必填）")
    private String cDwdz;

    @ApiModelProperty("邮编")
    private String cYzbm;

    @ApiModelProperty("证件类型（自然人）（被告非必填）")
    private String nZjlx;

    @ApiModelProperty("证照类型（当事人为法人或非法人组织时）")
    private String nZzlx;

    @ApiModelProperty("证照号码（当事人为法人或非法人组织时）")
    private String cZzjgdm;

    @ApiModelProperty("证件号码（自然人）")
    private String cZjhm;

    @ApiModelProperty("法定代表人姓名")
    private String cFddbrxm;

    @ApiModelProperty("法人代表人证件类型")
    private String nZjlxs;

    @ApiModelProperty("法人代表人证件号码")
    private String cDsrzjhm;

    @ApiModelProperty("电子邮箱")
    private String cDzyx;

    @ApiModelProperty("文化程度")
    private String nWhcd;

    @ApiModelProperty("职业")
    private String nZy;

    @ApiModelProperty("特殊生理或病理")
    private String cTsslhbl;
}
