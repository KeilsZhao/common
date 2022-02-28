package com.bzfar.vo.thrid;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("第三方返回开庭排期信息详情列表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThirdWaitInfoListVo {

    @JsonProperty("DD")
    private String dd;

    @JsonProperty("JSSJ")
    @ApiModelProperty("结束时间")
    private String jssj;

    @JsonProperty("FTMC")
    @ApiModelProperty("法庭名称")
    private String ftmc;

    @JsonProperty("FTYT")
    private String ftyt;

    @JsonProperty("KTRQ")
    @ApiModelProperty("开庭日期")
    private String ktrq;

    @JsonProperty("KTURL")
    private String kturl;

    @JsonProperty("CBR")
    @ApiModelProperty("承办人")
    private String cbr;

    @JsonProperty("LASTUPDATE")
    @ApiModelProperty("最后跟新时间")
    private String lastupdate;

    @JsonProperty("KTFT")
    @ApiModelProperty("开庭法庭")
    private String ktft;

    @JsonProperty("GKSL")
    private String gksl;

    @JsonProperty("SPZ")
    private String spz;

    @JsonProperty("KTSJ")
    @ApiModelProperty("开庭时间")
    private String ktsj;

    @JsonProperty("SJY")
    private String sjy;

    @JsonProperty("YDKTFTBH")
    private String ydktftbh;

    @JsonProperty("HYCY")
    private String hycy;

    @JsonProperty("AH")
    @ApiModelProperty("案号")
    private String ah;

    @JsonProperty("KTMS")
    private String ktms;

    @JsonProperty("GKKT")
    private String gkkt;

    @JsonProperty("AHDM")
    private String ahdm;

    @JsonProperty("TC")
    private String tc;

    @JsonProperty("XH")
    private String xh;

    @JsonProperty("SPZMS")
    private String spzms;

    @JsonProperty("YDKTFTMC")
    private String ydktftmc;

    @JsonProperty("FTBH")
    private String ftbh;

    @JsonProperty("CBRMS")
    private String cbrms;

    @JsonProperty("FYDMMS")
    @ApiModelProperty("法院代码明细")
    private String fydmms;

    @JsonProperty("SJYMS")
    private String sjyms;

    @JsonProperty("YDKTFYDM")
    private String ydktfydm;

    @JsonProperty("FYDM")
    private String fydm;
}
