package com.bzfar.po;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 刘成
 * @date 2021-6-4
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeadPo implements Serializable {

    @JacksonXmlProperty(localName = "DHDM")
    private String dhdm;

    @JacksonXmlProperty(localName = "JZLB")
    private String jzlb;

    @JacksonXmlProperty(localName = "CH")
    private String ch;

    @JacksonXmlProperty(localName = "CNYS")
    private String cnys;
}
