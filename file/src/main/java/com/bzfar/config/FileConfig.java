package com.bzfar.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file", ignoreUnknownFields = true)
@Data
public class FileConfig{

    /** 服务器存放的物理路径地址 */
    private String storePath;

    /** 静态路径地址 */
    private String httpPath;
}
