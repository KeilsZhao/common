package com.bzfar.vo.caseSearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("案件查询信息")
public class ThirdCaseVo {

    @ApiModelProperty("案由信息")
    @JsonProperty("AYMS")
    private String ayms;

    @ApiModelProperty("身份证号")
    @JsonProperty("SFZH")
    private String sfzh;

    @ApiModelProperty("律师资格证号")
    @JsonProperty("LSZGZH")
    private String lszgzh;

    @ApiModelProperty("社会信用代码")
    @JsonProperty("SHXYDM")
    private String shxydm;

    @ApiModelProperty("案件类别")
    @JsonProperty("AJLB")
    private String ajlb;

    @ApiModelProperty("当事人")
    @JsonProperty("DSR")
    private String dsr;

    @ApiModelProperty("承办人名称")
    @JsonProperty("CBRMC")
    private String cbrmc;

    @ApiModelProperty("案号")
    @JsonProperty("AH")
    private String ah;

    @ApiModelProperty("书记员名称")
    @JsonProperty("SJYMC")
    private String sjymc;

    @ApiModelProperty("jabdje")
    @JsonProperty("JABDJE")
    private String jabdje;

    @ApiModelProperty("案号代码")
    @JsonProperty("AHDM")
    private String ahdm;

    @ApiModelProperty("jafsms")
    @JsonProperty("JAFSMS")
    private String jafsms;

    @ApiModelProperty("结案日期")
    @JsonProperty("JARQ")
    private String jarq;

    @ApiModelProperty("承办部门")
    @JsonProperty("CBBM")
    private String cbbm;

    @ApiModelProperty("立案日期")
    @JsonProperty("LARQ")
    private String larq;

    @ApiModelProperty("审判长")
    @JsonProperty("SPZ")
    private String spz;

    @ApiModelProperty("法院代码")
    @JsonProperty("FYDM")
    private String fydm;

    @ApiModelProperty("案件状态")
    @JsonProperty("AJZT")
    private String ajzt;

    @ApiModelProperty("开庭信息")
    @JsonProperty("KTXX")
    private List<ThirdCaseKtxxVo> ctxx;
}
