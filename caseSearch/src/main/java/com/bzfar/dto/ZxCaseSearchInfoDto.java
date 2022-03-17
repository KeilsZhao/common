package com.bzfar.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ""
 */
@ApiModel(value = "案件查询请求参数-直接进入详情界面",description = "请求参数主要用于标准化产品使用，具体线上情况需根据具体情况进行部分修改")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZxCaseSearchInfoDto {

    @ApiModelProperty("身份证号")
    private String idCard;

    @ApiModelProperty("律师资格证号,当事人时不填")
    private String lawyerCard;

    @ApiModelProperty("律师查询案件时，当事人证件号校验")
    private String zjh;

    @ApiModelProperty("律师查询案件时，选择当事人的名称")
    private String name;

    @ApiModelProperty("案号")
    private String ah;

    @ApiModelProperty("年度")
    private String nd;

    @ApiModelProperty("代字")
    private String dz;

    @ApiModelProperty("序号")
    private String xh;

    @ApiModelProperty("法院代码，请求头获取")
    @JsonIgnore
    private String fydm;
}
