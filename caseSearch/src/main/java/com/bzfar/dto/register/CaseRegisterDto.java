package com.bzfar.dto.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("立案申请传参")
public class CaseRegisterDto {

    @ApiModelProperty("案件类型")
    @JsonProperty("ajlxdm")
    private String ajlxdm = "0301";

    @ApiModelProperty("jbfy")
    @JsonProperty("jbfy")
    private String jbfy = "4192";

    @ApiModelProperty("网上立案标识")
    @JsonProperty("wslabs")
    private String wslabs;

    @ApiModelProperty("立案统计案由")
    @JsonProperty("saay")
    private String saay;

    @ApiModelProperty("立案案由")
    @JsonProperty("ayms")
    private String ayms;

    @ApiModelProperty("标的金额")
    @JsonProperty("bdje")
    private String bdje;

    @ApiModelProperty("案件受理费")
    @JsonProperty("ajslf")
    private String ajslf;

    @ApiModelProperty("诉讼请求")
    @JsonProperty("ssqq")
    private String ssqq;

    @ApiModelProperty("材料")
    @JsonProperty("sqcl")
    private List<Object> sqcl;

    @ApiModelProperty("原告列表")
    @JsonProperty("ygParty")
    private List<Object> ygParty;

    @ApiModelProperty("被告列表")
    @JsonProperty("bgParty")
    private List<Object> bgParty;

    @ApiModelProperty("原告代理人")
    @JsonProperty("ygAgent")
    private List<Object> ygAgent;

    @ApiModelProperty("被告代理人")
    @JsonProperty("bgAgent")
    private List<Object> bgAgent;


}
