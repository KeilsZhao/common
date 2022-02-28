package com.bzfar.vo.thrid;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("案件批量下载详情返回数据")
public class ThirdCaseBatchInfoVo {


    @JsonProperty("AYMS")
    @ApiModelProperty("案由明细")
    private String ayms;

    @JsonProperty("AJLB")
    @ApiModelProperty("案件类别")
    private String ajlb;

    @JsonProperty("FGZLBS")
    private String fgzlbs;

    @JsonProperty("FDSX")
    private String fdsx;

    @JsonProperty("CBBMMS")
    private String cbbmms;

    @JsonProperty("SJYBS")
    private String sjybs;

    @JsonProperty("CBBM1")
    private String cbbm1;

    @JsonProperty("FJM")
    private String fjm;

    @JsonProperty("KTDD")
    private String ktdd;

    @JsonProperty("KTRQ")
    private String ktrq;

    @JsonProperty("XLA")
    private String xla;

    @JsonProperty("JAFSMS")
    private String jafsms;

    @JsonProperty("LASTUPDATE")
    private String lastupdate;

    @JsonProperty("CBR")
    @ApiModelProperty("承办人")
    private String cbr;

    @JsonProperty("FGZL")
    private String fgzl;

    @JsonProperty("JARQ")
    private String jarq;

    @JsonProperty("DZ")
    private String dz;

    @JsonProperty("LARQ")
    private String larq;

    @JsonProperty("SPZBS")
    private String spzbs;

    @JsonProperty("KSSJ")
    private String kssj;

    @JsonProperty("SPZ")
    private String spz;

    @JsonProperty("FGZLMS")
    private String fgzlms;

    @JsonProperty("AJMC")
    @ApiModelProperty("案件名称")
    private String ajmc;

    @JsonProperty("BDJE")
    private String bdje;

    @JsonProperty("DSR")
    @ApiModelProperty("当事人")
    private String dsr;

    @JsonProperty("SJY")
    private String sjy;

    @JsonProperty("HYCY")
    private String hycy;

    @JsonProperty("JAFS")
    private String jafs;

    @JsonProperty("SSQQ")
    private String ssqq;

    @JsonProperty("AJLXBS")
    private String ajlxbs;

    @JsonProperty("AH")
    @ApiModelProperty("案号")
    private String ah;

    @JsonProperty("YSAH")
    private String ysah;

    @JsonProperty("AJLY")
    private String ajly;

    @JsonProperty("AJLXMC")
    private String ajlxmc;

    @JsonProperty("CBRBS")
    private String cbrbs;

    @JsonProperty("AHDM")
    private String ahdm;

    @JsonProperty("AJZTMS")
    private String ajztms;

    @JsonProperty("SPZMS")
    private String spzms;

    @JsonProperty("SPCX")
    private String spcx;

    @JsonProperty("CBRMS")
    private String cbrms;

    @JsonProperty("SXJMRQ")
    private String sxjmrq;

    @JsonProperty("ZT")
    private String zt;

    @JsonProperty("FYDMMS")
    @ApiModelProperty("法院代码明细")
    private String fydmms;

    @JsonProperty("SJYMS")
    private String sjyms;

    @JsonProperty("SYCX")
    private String sycx;

    @JsonProperty("FYDM")
    private String fydm;
}
