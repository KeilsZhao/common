//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2021.05.25 时间 03:24:59 PM CST 
//


package com.bzfar.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MmsElementType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="MmsElementType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Unknown"/&gt;
 *     &lt;enumeration value="Jpg"/&gt;
 *     &lt;enumeration value="Gif"/&gt;
 *     &lt;enumeration value="Png"/&gt;
 *     &lt;enumeration value="Bmp"/&gt;
 *     &lt;enumeration value="Smil"/&gt;
 *     &lt;enumeration value="Wav"/&gt;
 *     &lt;enumeration value="Mid"/&gt;
 *     &lt;enumeration value="Amr"/&gt;
 *     &lt;enumeration value="Mp3"/&gt;
 *     &lt;enumeration value="Text"/&gt;
 *     &lt;enumeration value="Xml"/&gt;
 *     &lt;enumeration value="Mp4"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "MmsElementType")
@XmlEnum
public enum MmsElementType {

    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),
    @XmlEnumValue("Jpg")
    JPG("Jpg"),
    @XmlEnumValue("Gif")
    GIF("Gif"),
    @XmlEnumValue("Png")
    PNG("Png"),
    @XmlEnumValue("Bmp")
    BMP("Bmp"),
    @XmlEnumValue("Smil")
    SMIL("Smil"),
    @XmlEnumValue("Wav")
    WAV("Wav"),
    @XmlEnumValue("Mid")
    MID("Mid"),
    @XmlEnumValue("Amr")
    AMR("Amr"),
    @XmlEnumValue("Mp3")
    MP_3("Mp3"),
    @XmlEnumValue("Text")
    TEXT("Text"),
    @XmlEnumValue("Xml")
    XML("Xml"),
    @XmlEnumValue("Mp4")
    MP_4("Mp4");
    private final String value;

    MmsElementType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MmsElementType fromValue(String v) {
        for (MmsElementType c: MmsElementType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
