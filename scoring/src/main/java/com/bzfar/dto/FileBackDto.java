package com.bzfar.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @author Fimipeler
 * @Description FileBackDto
 * @Date 2021/6/10 11:10
 */
@ApiModel("材料补交-文件上传请求参数")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JacksonXmlRootElement(localName = "case")
public class FileBackDto {

    @ApiModelProperty("案号代码")
    @JacksonXmlProperty(localName = "ahdm")
    private String ahdm;

    @ApiModelProperty("材料流水号")
    @JacksonXmlProperty(localName = "lsh")
    private String lsh;

    @ApiModelProperty("FTP服务器信息")
    @JacksonXmlProperty(localName = "ftp")
    private FileBackFtpDto ftpDto;

    @ApiModelProperty("文件信息")
    @JacksonXmlElementWrapper(localName = "files")
    @JacksonXmlProperty(localName = "file")
    private List<FileBackInfoDto> files;

}
