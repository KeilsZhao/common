package com.bzfar.utils;

import com.bzfar.exception.DataException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 标准版新版案件查询mock数据获取
 * @author Administrator
 */
public class MockUtil {

    /**
     * jar内部mock获取
     */
    private final static String filePath = "classpath*:mock1/";

    /**
     * jar同级文件获取
     */
    private final static String jarfilePath = System.getProperty("user.dir") + "/";

    /**
     * 根据文件名称获取mock数据流
     *
     * @param fileName 文件名称
     * @return
     */
    public static InputStream getFileIo(String fileName) {
        InputStream stream = null;
        try {
            File file = new File(jarfilePath, fileName);
            stream = new FileInputStream(file);
        } catch (Exception e) {
            try {
                ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
                Resource[] resources = resolver.getResources(filePath + fileName);
                stream = resources[0].getInputStream();
            } catch (Exception e1) {
                throw new DataException(e1.getMessage());
            }
        }
        return stream;
    }
}
