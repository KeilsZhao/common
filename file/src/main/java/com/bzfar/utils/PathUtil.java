package com.bzfar.utils;

import com.bzfar.config.FileConfig;
import com.bzfar.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 文件路径工具类
 * @author Administrator
 */
@Component
public class PathUtil {

    @Autowired
    private FileConfig fileConfig;

    /**
     * 拼接本地存储全路径
     *
     * @param fileName 文件名称
     * @return
     */
    public String concatStore(String fileName){
        String storePath = fileConfig.getStorePath();
        AssertUtil.assertEmpty(storePath, "物理配置路径有误，请检查配置文件");
        String newPath = storePath.endsWith("/") ? storePath : storePath + "/";
        return newPath + fileName;
    }

    /**
     * 拼接网络路径全路径
     *
     * @param fileName 文件名称
     * @return
     */
    public String concatHttp(String fileName){
        String httpPath = fileConfig.getHttpPath();
        AssertUtil.assertEmpty(httpPath, "网络配置路径有误，请检查配置文件");
        String newPath = httpPath.endsWith("/") ? httpPath : httpPath + "/";
        return newPath + fileName;
    }

}
