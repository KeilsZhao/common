package com.bzfar.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Fimipeler
 * @Description FileBackFtpDto
 * @Date 2021/6/10 11:51
 */
@ApiModel("材料补交-文件上传-Ftp服务器信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileBackFtpDto {

    @ApiModelProperty("ftp服务器ip地址，前端不用管")
    @JacksonXmlProperty(localName = "ip")
    private String ip;

    @ApiModelProperty("ftp服务器端口，前端不用管")
    @JacksonXmlProperty(localName = "port")
    private String port;

    @ApiModelProperty("ftp服务器用户名，前端不用管")
    @JacksonXmlProperty(localName = "user")
    private String user;

    @ApiModelProperty("ftp服务器密码，前端不用管")
    @JacksonXmlProperty(localName = "pwsswd")
    private String password;

    @ApiModelProperty("ftp服务器文件存储路径，前端不用管")
    @JacksonXmlProperty(localName = "lj")
    private String filePath;
}
