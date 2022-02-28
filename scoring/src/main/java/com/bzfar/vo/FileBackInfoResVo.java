package com.bzfar.vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Fimipeler
 * @Description FileBackInfoResVo
 * @Date 2021/6/10 14:37
 */
@ApiModel("材料补交-返回信息response")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileBackInfoResVo {

    @ApiModelProperty("返回码")
    @JacksonXmlProperty(localName = "code")
    private String code;

    @ApiModelProperty("返回信息")
    @JacksonXmlProperty(localName = "errmsg")
    private String msg;
}
