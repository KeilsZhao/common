package com.bzfar.factory;

import com.bzfar.enums.FormatConversionEnum;
import com.bzfar.service.FormatConversionService;
import com.bzfar.utils.SpringContextUtil;

/**
 * @author ""
 * @Description FormatConversionFactory
 * @Date 2021/11/30 10:28
 */
public class FormatConversionFactory {

    /**
     * 获取具体实现类
     * @param formatConversionEnum
     * @return
     */
    public static FormatConversionService getFormatConversionService(FormatConversionEnum formatConversionEnum){
        Class<?> aClass = null;
        try {
            String path = formatConversionEnum.getPackageUrl();
            aClass =  Class.forName(path);
            return (FormatConversionService)SpringContextUtil.getBean(aClass);
        } catch (Exception e) {
            return null;
        }
    }

}
