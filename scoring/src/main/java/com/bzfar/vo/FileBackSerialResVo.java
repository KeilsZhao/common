package com.bzfar.vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Fimipeler
 * @Description FileBackSerialResVo
 * @Date 2021/6/10 10:19
 */
@ApiModel("材料补交-流水号获取-第三方返回信息Response")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileBackSerialResVo {

    @ApiModelProperty("返回结果码")
    @JacksonXmlProperty(localName = "code")
    private String code;

    @ApiModelProperty("流水号")
    @JacksonXmlProperty(localName = "lsh")
    private String serialNum;

}
