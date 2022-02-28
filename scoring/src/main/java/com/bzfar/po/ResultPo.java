package com.bzfar.po;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 刘成
 * @date 2021-6-3
 */
@Data
public class ResultPo {

    @JacksonXmlProperty(localName = "Code")
    @ApiModelProperty("返回值")
    private String code;
}
