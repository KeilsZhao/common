package com.bzfar.vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Fimipeler
 * @Description FileBackSerialVo
 * @Date 2021/6/10 10:17
 */
@ApiModel("材料补交-流水号获取-第三方返回信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "case")
public class FileBackSerialVo {

    @ApiModelProperty("返回信息")
    @JacksonXmlProperty(localName = "response")
    private FileBackSerialResVo resVo;

}
