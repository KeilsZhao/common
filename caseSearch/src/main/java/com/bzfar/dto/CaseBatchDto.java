package com.bzfar.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "Request")
public class CaseBatchDto {

    @JacksonXmlProperty(localName = "LARQ")
    @ApiModelProperty("立案日期 日期格式： YYYY-MM-DD")
    private String larq;

    @JacksonXmlProperty(localName = "ZT")
    @ApiModelProperty("状态: 1已结案，0未结案，查询已结案案件时，必须输入结案日期条件")
    private String zt = "0";

    @JacksonXmlProperty(localName = "JARQ")
    @ApiModelProperty("结案日期")
    private String jarq = zt.equals("1") ? "1996-" : "";

    @JacksonXmlProperty(localName = "DSR")
    @ApiModelProperty("当事人名称")
    private String dsr;

    @JacksonXmlProperty(localName = "AH")
    @ApiModelProperty("案号")
    private String ah;

    @JacksonXmlProperty(localName = "AHDM")
    @ApiModelProperty("案号代码")
    private String ahdm;

    @JacksonXmlProperty(localName = "CBR")
    @ApiModelProperty("承办人")
    private String cbr;

    @JacksonXmlProperty(localName = "ZHGXSJ")
    @ApiModelProperty("(案件)最后更新时间 格式：YYYY-MM-DD HH:MM:SS 通过此参数可实现案件增量下载")
    private String zhgxsj;

    @JacksonXmlProperty(localName = "PAGENUM")
    @ApiModelProperty("页数")
    private String pagenum;
}
