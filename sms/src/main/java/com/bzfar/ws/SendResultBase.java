//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2021.05.25 时间 03:24:59 PM CST 
//


package com.bzfar.ws;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SendResultBase complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SendResultBase"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PayType" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="StatusCode" type="{http://www.yysms.com/}ResultCode"/&gt;
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MsgId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SuccessCounts" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="BillingCount" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="Balance" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="BillingAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="Errors" type="{http://www.yysms.com/}ArrayOfString" minOccurs="0"/&gt;
 *         &lt;element name="AlterSendResult" type="{http://www.yysms.com/}SendResultBase" minOccurs="0"/&gt;
 *         &lt;element name="CustomId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SendResultBase", propOrder = {
    "payType",
    "statusCode",
    "description",
    "msgId",
    "successCounts",
    "billingCount",
    "amount",
    "balance",
    "billingAmount",
    "errors",
    "alterSendResult",
    "customId"
})
@XmlSeeAlso({
    SendResult.class,
    I18NSendResult.class
})
public abstract class SendResultBase {

    @XmlElement(name = "PayType")
    protected int payType;
    @XmlElement(name = "StatusCode", required = true)
    @XmlSchemaType(name = "string")
    protected ResultCode statusCode;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "MsgId")
    protected String msgId;
    @XmlElement(name = "SuccessCounts")
    protected int successCounts;
    @XmlElement(name = "BillingCount")
    protected int billingCount;
    @XmlElement(name = "Amount")
    protected long amount;
    @XmlElement(name = "Balance", required = true)
    protected BigDecimal balance;
    @XmlElement(name = "BillingAmount", required = true)
    protected BigDecimal billingAmount;
    @XmlElement(name = "Errors")
    protected ArrayOfString errors;
    @XmlElement(name = "AlterSendResult")
    protected SendResultBase alterSendResult;
    @XmlElement(name = "CustomId")
    protected String customId;

    /**
     * 获取payType属性的值。
     * 
     */
    public int getPayType() {
        return payType;
    }

    /**
     * 设置payType属性的值。
     * 
     */
    public void setPayType(int value) {
        this.payType = value;
    }

    /**
     * 获取statusCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ResultCode }
     *     
     */
    public ResultCode getStatusCode() {
        return statusCode;
    }

    /**
     * 设置statusCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ResultCode }
     *     
     */
    public void setStatusCode(ResultCode value) {
        this.statusCode = value;
    }

    /**
     * 获取description属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置description属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * 获取msgId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * 设置msgId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgId(String value) {
        this.msgId = value;
    }

    /**
     * 获取successCounts属性的值。
     * 
     */
    public int getSuccessCounts() {
        return successCounts;
    }

    /**
     * 设置successCounts属性的值。
     * 
     */
    public void setSuccessCounts(int value) {
        this.successCounts = value;
    }

    /**
     * 获取billingCount属性的值。
     * 
     */
    public int getBillingCount() {
        return billingCount;
    }

    /**
     * 设置billingCount属性的值。
     * 
     */
    public void setBillingCount(int value) {
        this.billingCount = value;
    }

    /**
     * 获取amount属性的值。
     * 
     */
    public long getAmount() {
        return amount;
    }

    /**
     * 设置amount属性的值。
     * 
     */
    public void setAmount(long value) {
        this.amount = value;
    }

    /**
     * 获取balance属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 设置balance属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBalance(BigDecimal value) {
        this.balance = value;
    }

    /**
     * 获取billingAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBillingAmount() {
        return billingAmount;
    }

    /**
     * 设置billingAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBillingAmount(BigDecimal value) {
        this.billingAmount = value;
    }

    /**
     * 获取errors属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getErrors() {
        return errors;
    }

    /**
     * 设置errors属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setErrors(ArrayOfString value) {
        this.errors = value;
    }

    /**
     * 获取alterSendResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SendResultBase }
     *     
     */
    public SendResultBase getAlterSendResult() {
        return alterSendResult;
    }

    /**
     * 设置alterSendResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SendResultBase }
     *     
     */
    public void setAlterSendResult(SendResultBase value) {
        this.alterSendResult = value;
    }

    /**
     * 获取customId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomId() {
        return customId;
    }

    /**
     * 设置customId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomId(String value) {
        this.customId = value;
    }

}
