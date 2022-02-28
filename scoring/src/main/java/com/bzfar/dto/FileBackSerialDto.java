package com.bzfar.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Fimipeler
 * @Description FileBackDto
 * @Date 2021/6/9 17:54
 */
@ApiModel("材料补交-流水号获取传参")
@JacksonXmlRootElement(localName = "case")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileBackSerialDto {

    @ApiModelProperty("信息来源")
    @JacksonXmlProperty(localName = "xxly")
    private String msgCome;

    @ApiModelProperty("业务类型")
    @JacksonXmlProperty(localName = "ywlx")
    private String busiType;

    @ApiModelProperty("法院代码")
    @JacksonXmlProperty(localName = "slfy")
    private String fydm;

    @ApiModelProperty("材料来源")
    @JacksonXmlProperty(localName = "clly")
    private String fileCome;

    @ApiModelProperty("提交人姓名")
    @JacksonXmlProperty(localName = "tjr")
    private String subUserName;

    @ApiModelProperty("当事人名称")
    @JacksonXmlProperty(localName = "dsr")
    private String dsrUserName;

    @ApiModelProperty("提交形式")
    @JacksonXmlProperty(localName = "tjxs")
    private String subType;

    @ApiModelProperty("涵义未知，固定值：320500zhangsan")
    @JacksonXmlProperty(localName = "sjr")
    private String sjr;

    @ApiModelProperty("涵义未知，传空字符串")
    @JacksonXmlProperty(localName = "ygbs")
    private String ygbs;

    @ApiModelProperty("涵义未知，传空字符串")
    @JacksonXmlProperty(localName = "sjfy")
    private String sjfy;

    @ApiModelProperty("涵义未知，传空字符串")
    @JacksonXmlProperty(localName = "bz")
    private String bz;

    @ApiModelProperty("案件信息")
    @JacksonXmlProperty(localName = "ajxx")
    private FileBackSerialCaseDto fileBackCaseDto;
}
