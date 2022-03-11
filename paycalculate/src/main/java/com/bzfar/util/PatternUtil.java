package com.bzfar.util;

import com.bzfar.enums.ResponseCode;
import com.bzfar.exception.DataException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则校验
 * @author Administrator
 */
public class PatternUtil {

    private static Pattern pattern = Pattern.compile("^-?[0-9]+");

    /**
     * 校验纯数字
     *
     * @param num 数字
     * @param message 错误信息
     */
    public static void checkNum(String num,String message){
        Matcher matcher = pattern.matcher(num);
        if (!matcher.matches()){
            throw new DataException(ResponseCode.BAD_REQUEST,message);
        }
    }

}
