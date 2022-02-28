package com.bzfar.util;

import java.math.BigDecimal;

/**
 * 自定义比较大小
 */
public class BigDecimalUtil {

    /**
     * a大于b
     *
     * @return
     */
    public static Boolean bgt(BigDecimal a , BigDecimal b){
        if (a.compareTo(b) == 1) {
            return true;
        }
        return false;
    }

    /**
     * a大于等于b
     * @return
     */
    public static Boolean bge(BigDecimal a , BigDecimal b){
        if(a.compareTo(b) == 1 || a.compareTo(b) == 0) {
            return true;
        }
        return false;
    }

    /**
     * a等于b
     * @return
     */
    public static Boolean bet(BigDecimal a , BigDecimal b){
        if(a.compareTo(b) == 0) {
            return true;
        }
        return false;
    }

    /**
     * a小于b
     * @return
     */
    public static Boolean blt(BigDecimal a , BigDecimal b){
        if(a.compareTo(b) == -1) {
            return true;
        }
        return false;
    }

    /**
     * a小于等于b
     * @return
     */
    public static Boolean ble(BigDecimal a , BigDecimal b){
        if(a.compareTo(b) == -1 || a.compareTo(b) == 0) {
            return true;
        }
        return false;
    }
}
