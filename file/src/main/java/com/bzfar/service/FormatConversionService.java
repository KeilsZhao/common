package com.bzfar.service;

import com.bzfar.vo.FormatVo;
import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ""
 * @Description FormatConversionService
 * @Date 2021/11/29 11:24
 */
public interface FormatConversionService {

    /**
     * 格式转换
     *
     * @param oldType        旧文件类型
     * @param sourceFilePath 文件流
     * @return
     */
    FormatVo conversionFormat(String oldType, String sourceFilePath);

}
