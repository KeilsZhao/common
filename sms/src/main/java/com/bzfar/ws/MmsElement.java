//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2021.05.25 时间 03:24:59 PM CST 
//


package com.bzfar.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MmsElement complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="MmsElement"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ElementType" type="{http://www.yysms.com/}MmsElementType"/&gt;
 *         &lt;element name="Base64Text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MmsElement", propOrder = {
    "name",
    "elementType",
    "base64Text"
})
public class MmsElement {

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "ElementType", required = true)
    @XmlSchemaType(name = "string")
    protected MmsElementType elementType;
    @XmlElement(name = "Base64Text")
    protected String base64Text;

    /**
     * 获取name属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获取elementType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MmsElementType }
     *     
     */
    public MmsElementType getElementType() {
        return elementType;
    }

    /**
     * 设置elementType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MmsElementType }
     *     
     */
    public void setElementType(MmsElementType value) {
        this.elementType = value;
    }

    /**
     * 获取base64Text属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBase64Text() {
        return base64Text;
    }

    /**
     * 设置base64Text属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBase64Text(String value) {
        this.base64Text = value;
    }

}
