package com.bzfar.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum ResponseCode {

    /**
     * 返回码
     */
    SUCCESS(200, "成功"),
    BAD_REQUEST(400, "参数错误"),
    UNAUTHORIZED(401, "鉴权失败"),
    FILE_NOT_FOUND(404 , "文件不存在"),
    FILE_TRANSFORM_ERROR(405 , "文件转化失败"),
    SERVER_ERROR(504, "服务器错误"),
    SAVE_ERROR(505 , "数据入库失败"),
    THIRD_ERROR(506 , "第三方数据错误"),
    BOX_FULL(600 , "柜格已满，不存在空闲的柜格"),
    FILE_TIME(601 , "文件过期"),
    FILE_SAVE_ERROR(602 , "文件保存入硬盘失败"),
    PHONE_ERROR(700 , "手机号码验证错误"),
    FRIENDLY_ERROR(900, "提示性返回信息"),
    DATA_ERROR(12132, "暂无数据"),
    LOGIN_FAILURE(12015, "用户名或密码错误"),
    TOKEN_MISS(40316, "token不存在"),
    TOKEN_EXPIRED(40317, "token已过期"),
    REFRESH_TOKEN_FAILURE(403178, "刷新token失败"),
    FYDM_MISS(500000, "法院代码不存在"),
    SBBH_MISS(500001,"设备编号不存在"),
    SBINFO_MISS(500002,"设备信息无法获取"),

    //交易api错误码
    API_TRADE_COMMON_NO_ERROR(900000, "系统错误"),
    API_TRADE_DISCOUNT_NO_USER(900001, "用户不存在"),
    //H5api错误码
    API_H5_COMMON_NO_ERROR(910000, "系统错误"),
    API_H5_COMMON_NO_USER(910001, "用户不存在"),;

    private Integer code;
    private String msg;

    ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResponseCode getFromCode(String code) {
        for (ResponseCode value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }

}
