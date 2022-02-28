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
 * <p>ResultCode的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="ResultCode"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Unknown"/&gt;
 *     &lt;enumeration value="OK"/&gt;
 *     &lt;enumeration value="AppInactive"/&gt;
 *     &lt;enumeration value="ParamError"/&gt;
 *     &lt;enumeration value="UserNameEmpty"/&gt;
 *     &lt;enumeration value="PasswordEmpty"/&gt;
 *     &lt;enumeration value="UserNameError"/&gt;
 *     &lt;enumeration value="PasswordError"/&gt;
 *     &lt;enumeration value="BindIpError"/&gt;
 *     &lt;enumeration value="UserStop"/&gt;
 *     &lt;enumeration value="UserIdError"/&gt;
 *     &lt;enumeration value="Text64Error"/&gt;
 *     &lt;enumeration value="StampError"/&gt;
 *     &lt;enumeration value="SendTimeError"/&gt;
 *     &lt;enumeration value="JsonError"/&gt;
 *     &lt;enumeration value="MsisdnEmpty"/&gt;
 *     &lt;enumeration value="MsisdnRepeat"/&gt;
 *     &lt;enumeration value="NoChannel"/&gt;
 *     &lt;enumeration value="MsisdnError"/&gt;
 *     &lt;enumeration value="MsisdnBlack"/&gt;
 *     &lt;enumeration value="MsisdnNoChannel"/&gt;
 *     &lt;enumeration value="AmountNotEnough"/&gt;
 *     &lt;enumeration value="NoProduct"/&gt;
 *     &lt;enumeration value="ExtNumberError"/&gt;
 *     &lt;enumeration value="BalanceNotEnough"/&gt;
 *     &lt;enumeration value="ChannelTypeError"/&gt;
 *     &lt;enumeration value="TemplateError"/&gt;
 *     &lt;enumeration value="FrequentOperation"/&gt;
 *     &lt;enumeration value="NoEnabled"/&gt;
 *     &lt;enumeration value="TemplateIdError"/&gt;
 *     &lt;enumeration value="ContentEmpty"/&gt;
 *     &lt;enumeration value="ContentLong"/&gt;
 *     &lt;enumeration value="MsisdnsMany"/&gt;
 *     &lt;enumeration value="SensitiveWords"/&gt;
 *     &lt;enumeration value="ContentError"/&gt;
 *     &lt;enumeration value="MsisdnLess"/&gt;
 *     &lt;enumeration value="RequiredSuffix"/&gt;
 *     &lt;enumeration value="SuffixError"/&gt;
 *     &lt;enumeration value="SubjectEmpty"/&gt;
 *     &lt;enumeration value="SubjectLong"/&gt;
 *     &lt;enumeration value="FramesEmpty"/&gt;
 *     &lt;enumeration value="ElementError"/&gt;
 *     &lt;enumeration value="FrameError"/&gt;
 *     &lt;enumeration value="FrameSizeLong"/&gt;
 *     &lt;enumeration value="SmilEmpty"/&gt;
 *     &lt;enumeration value="SmilExcessive"/&gt;
 *     &lt;enumeration value="ElementNameRepeat"/&gt;
 *     &lt;enumeration value="SizeLong"/&gt;
 *     &lt;enumeration value="MmsIdEmpty"/&gt;
 *     &lt;enumeration value="NoMmsTemplate"/&gt;
 *     &lt;enumeration value="TaskIdEmpty"/&gt;
 *     &lt;enumeration value="NoRealName"/&gt;
 *     &lt;enumeration value="FaileList"/&gt;
 *     &lt;enumeration value="InternalError"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ResultCode")
@XmlEnum
public enum ResultCode {

    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),
    OK("OK"),
    @XmlEnumValue("AppInactive")
    APP_INACTIVE("AppInactive"),
    @XmlEnumValue("ParamError")
    PARAM_ERROR("ParamError"),
    @XmlEnumValue("UserNameEmpty")
    USER_NAME_EMPTY("UserNameEmpty"),
    @XmlEnumValue("PasswordEmpty")
    PASSWORD_EMPTY("PasswordEmpty"),
    @XmlEnumValue("UserNameError")
    USER_NAME_ERROR("UserNameError"),
    @XmlEnumValue("PasswordError")
    PASSWORD_ERROR("PasswordError"),
    @XmlEnumValue("BindIpError")
    BIND_IP_ERROR("BindIpError"),
    @XmlEnumValue("UserStop")
    USER_STOP("UserStop"),
    @XmlEnumValue("UserIdError")
    USER_ID_ERROR("UserIdError"),
    @XmlEnumValue("Text64Error")
    TEXT_64_ERROR("Text64Error"),
    @XmlEnumValue("StampError")
    STAMP_ERROR("StampError"),
    @XmlEnumValue("SendTimeError")
    SEND_TIME_ERROR("SendTimeError"),
    @XmlEnumValue("JsonError")
    JSON_ERROR("JsonError"),
    @XmlEnumValue("MsisdnEmpty")
    MSISDN_EMPTY("MsisdnEmpty"),
    @XmlEnumValue("MsisdnRepeat")
    MSISDN_REPEAT("MsisdnRepeat"),
    @XmlEnumValue("NoChannel")
    NO_CHANNEL("NoChannel"),
    @XmlEnumValue("MsisdnError")
    MSISDN_ERROR("MsisdnError"),
    @XmlEnumValue("MsisdnBlack")
    MSISDN_BLACK("MsisdnBlack"),
    @XmlEnumValue("MsisdnNoChannel")
    MSISDN_NO_CHANNEL("MsisdnNoChannel"),
    @XmlEnumValue("AmountNotEnough")
    AMOUNT_NOT_ENOUGH("AmountNotEnough"),
    @XmlEnumValue("NoProduct")
    NO_PRODUCT("NoProduct"),
    @XmlEnumValue("ExtNumberError")
    EXT_NUMBER_ERROR("ExtNumberError"),
    @XmlEnumValue("BalanceNotEnough")
    BALANCE_NOT_ENOUGH("BalanceNotEnough"),
    @XmlEnumValue("ChannelTypeError")
    CHANNEL_TYPE_ERROR("ChannelTypeError"),
    @XmlEnumValue("TemplateError")
    TEMPLATE_ERROR("TemplateError"),
    @XmlEnumValue("FrequentOperation")
    FREQUENT_OPERATION("FrequentOperation"),
    @XmlEnumValue("NoEnabled")
    NO_ENABLED("NoEnabled"),
    @XmlEnumValue("TemplateIdError")
    TEMPLATE_ID_ERROR("TemplateIdError"),
    @XmlEnumValue("ContentEmpty")
    CONTENT_EMPTY("ContentEmpty"),
    @XmlEnumValue("ContentLong")
    CONTENT_LONG("ContentLong"),
    @XmlEnumValue("MsisdnsMany")
    MSISDNS_MANY("MsisdnsMany"),
    @XmlEnumValue("SensitiveWords")
    SENSITIVE_WORDS("SensitiveWords"),
    @XmlEnumValue("ContentError")
    CONTENT_ERROR("ContentError"),
    @XmlEnumValue("MsisdnLess")
    MSISDN_LESS("MsisdnLess"),
    @XmlEnumValue("RequiredSuffix")
    REQUIRED_SUFFIX("RequiredSuffix"),
    @XmlEnumValue("SuffixError")
    SUFFIX_ERROR("SuffixError"),
    @XmlEnumValue("SubjectEmpty")
    SUBJECT_EMPTY("SubjectEmpty"),
    @XmlEnumValue("SubjectLong")
    SUBJECT_LONG("SubjectLong"),
    @XmlEnumValue("FramesEmpty")
    FRAMES_EMPTY("FramesEmpty"),
    @XmlEnumValue("ElementError")
    ELEMENT_ERROR("ElementError"),
    @XmlEnumValue("FrameError")
    FRAME_ERROR("FrameError"),
    @XmlEnumValue("FrameSizeLong")
    FRAME_SIZE_LONG("FrameSizeLong"),
    @XmlEnumValue("SmilEmpty")
    SMIL_EMPTY("SmilEmpty"),
    @XmlEnumValue("SmilExcessive")
    SMIL_EXCESSIVE("SmilExcessive"),
    @XmlEnumValue("ElementNameRepeat")
    ELEMENT_NAME_REPEAT("ElementNameRepeat"),
    @XmlEnumValue("SizeLong")
    SIZE_LONG("SizeLong"),
    @XmlEnumValue("MmsIdEmpty")
    MMS_ID_EMPTY("MmsIdEmpty"),
    @XmlEnumValue("NoMmsTemplate")
    NO_MMS_TEMPLATE("NoMmsTemplate"),
    @XmlEnumValue("TaskIdEmpty")
    TASK_ID_EMPTY("TaskIdEmpty"),
    @XmlEnumValue("NoRealName")
    NO_REAL_NAME("NoRealName"),
    @XmlEnumValue("FaileList")
    FAILE_LIST("FaileList"),
    @XmlEnumValue("InternalError")
    INTERNAL_ERROR("InternalError");
    private final String value;

    ResultCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ResultCode fromValue(String v) {
        for (ResultCode c: ResultCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
