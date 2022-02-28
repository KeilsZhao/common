package com.bzfar.vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Fimipeler
 * @Description FileBackInfoVo
 * @Date 2021/6/10 14:35
 */
@ApiModel("材料补交-返回信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JacksonXmlRootElement(localName = "case")
public class FileBackInfoVo {

    @ApiModelProperty("返回信息")
    @JacksonXmlProperty(localName = "response")
    private FileBackInfoResVo fileBackInfoResVo;

}
