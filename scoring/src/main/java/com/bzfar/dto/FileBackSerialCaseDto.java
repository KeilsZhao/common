package com.bzfar.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Fimipeler
 * @Description FileBackCaseDto
 * @Date 2021/6/9 18:20
 */
@ApiModel("材料补交-流水码获取传参-案件信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileBackSerialCaseDto {

    @ApiModelProperty("案件类别 2民事")
    @JacksonXmlProperty(localName = "ajlb")
    private String caseCate;

    @ApiModelProperty("法院代码")
    @JacksonXmlProperty(localName = "jbfy")
    private String fydm;

    @ApiModelProperty("案号")
    @JacksonXmlProperty(localName = "ah")
    private String ah;

    @ApiModelProperty("案号代码")
    @JacksonXmlProperty(localName = "ahdm")
    private String ahdm;

    @ApiModelProperty("案件类型")
    @JacksonXmlProperty(localName = "ajlx")
    private String caseType;
}
