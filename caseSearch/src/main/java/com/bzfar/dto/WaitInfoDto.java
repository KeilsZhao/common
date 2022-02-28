package com.bzfar.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("开庭排期信息下载")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "Request")
public class WaitInfoDto {

    @JacksonXmlProperty(localName = "AHDM")
    @ApiModelProperty("案号代码")
    private String ahdm;

    @JacksonXmlProperty(localName = "KTRQ")
    @ApiModelProperty("日期，日期1-开庭日期2，表示查询介于日期1与日期2之间的数据，日期1-，表示查询大于等于日期1的数据")
    private String ktrq;

    @JacksonXmlProperty(localName = "OPTYPE")
    @ApiModelProperty("是否同步下载异地开庭信息,如果值=ydkt，则可下载异地开庭信息，其他则不下载")
    private String optype;

    @JacksonXmlProperty(localName = "PAGENUM")
    @ApiModelProperty(" 页数")
    private String pagenum;
}
