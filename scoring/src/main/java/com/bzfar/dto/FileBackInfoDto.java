package com.bzfar.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Fimipeler
 * @Description FileBackInfoDto
 * @Date 2021/6/10 11:58
 */
@ApiModel("文件补交-文件信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileBackInfoDto {

    @ApiModelProperty("文件序号")
    @JacksonXmlProperty(localName = "xh")
    private String xh;

    @ApiModelProperty("文件后缀名")
    @JacksonXmlProperty(localName = "wjgs")
    private String fileBeh;

    @ApiModelProperty("文件名称")
    @JacksonXmlProperty(localName = "wjmc")
    private String fileName;

    @ApiModelProperty("文件路径")
    @JacksonXmlProperty(localName = "wjlj")
    private String filePath;

}
