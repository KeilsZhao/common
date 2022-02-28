package com.bzfar.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fimipeler
 * @Description FileBackConfig,材料补交配置项
 * @Date 2021/6/10 11:20
 */
@Configuration
@ConfigurationProperties(prefix = "file.back")
@Data
public class FileBackConfig {

    /**
     * 材料补交-上传文件的Ftp服务器IP地址
     */
    private String ftpHost;

    /**
     * 材料补交-上传文件的Ftp服务器端口
     */
    private String ftpPort;

    /**
     * 材料补交-上传文件Ftp服务器用户名
     */
    private String ftpUsername;

    /**
     * 材料补交-上传文件Ftp服务器密码
     */
    private String ftpPassword;

}
