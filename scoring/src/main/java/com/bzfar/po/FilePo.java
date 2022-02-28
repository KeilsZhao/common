package com.bzfar.po;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

/**
 * @author 刘成
 * @date 2021-6-4
 */
@Data
public class FilePo {

    @JacksonXmlProperty(localName = "JZLB")
    private String jzlb;

    @JacksonXmlProperty(localName = "CH")
    private String ch;

    @JacksonXmlProperty(localName = "PGTYPE")
    private String pgtype;

    @JacksonXmlProperty(localName = "YEMA")
    private String yema;

    @JacksonXmlProperty(localName = "FTPSERVER")
    private String ftpServer;

    @JacksonXmlProperty(localName = "OSSNAME")
    private String ossName;

    @JacksonXmlProperty(localName = "FILENAME")
    private String fileName;

    @JacksonXmlProperty(localName = "OSSFILENAME")
    private String ossFileName;
}
