package com.bzfar.po;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 刘成
 * @date 2021-6-4
 */
@Data
@ApiModel("ftp信息")
public class FtpServerPo {

    @JacksonXmlProperty(localName = "FTPNAME")
    @ApiModelProperty("ftp名称")
    private String ftpName;

    @JacksonXmlProperty(localName = "IPADDR")
    @ApiModelProperty("ip地址")
    private String ipAddr;

    @JacksonXmlProperty(localName = "FTPPORT")
    @ApiModelProperty("ftp端口")
    private String ftpPort;

    @JacksonXmlProperty(localName = "USRNAME")
    @ApiModelProperty("ftp账号")
    private String userName;

    @JacksonXmlProperty(localName = "PASSWD")
    @ApiModelProperty("ftp密码")
    private String passWord;
}
