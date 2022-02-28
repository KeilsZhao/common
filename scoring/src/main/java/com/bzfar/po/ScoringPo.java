package com.bzfar.po;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

/**
 * @author 刘成
 * @date 2021-6-4
 */
@Data
public class ScoringPo {

    @JacksonXmlProperty(localName = "JZLB")
    private String jzlb;

    @JacksonXmlProperty(localName = "CH")
    private String ch;

    @JacksonXmlProperty(localName = "XH")
    private String xh;

    @JacksonXmlProperty(localName = "CLBT")
    private String clbt;

    @JacksonXmlProperty(localName = "P1")
    private String p1;
}
