package com.bzfar.po;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 刘成
 * @date 2021-6-3
 */
@ApiModel("通达海第三方返回信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "Response")
public class TongDaHaiScoringPo {

    @JacksonXmlProperty(localName = "Result")
    @ApiModelProperty("返回信息")
    private ResultPo resultPo;

    @JacksonXmlProperty(localName = "DA_SSJCXX")
    @JacksonXmlElementWrapper(localName = "DA_SSJCXX_LIST")
    @ApiModelProperty("主体信息")
    private List<HeadPo> headList;

    @JacksonXmlProperty(localName = "DA_SSJCBM")
    @JacksonXmlElementWrapper(localName = "DA_SSJCBM_LIST")
    private List<ScoringPo> scoringList;

    @JacksonXmlProperty(localName = "DA_SSJCYX")
    @JacksonXmlElementWrapper(localName = "DA_SSJCYX_LIST")
    @ApiModelProperty("文件地址列表")
    private List<FilePo> FileList;

    @JacksonXmlProperty(localName = "DA_FTPSERVER")
    @JacksonXmlElementWrapper(localName = "DA_FTPSERVER_LIST")
    @ApiModelProperty("ftp信息集")
    private List<FtpServerPo> ftpServerList;
}
