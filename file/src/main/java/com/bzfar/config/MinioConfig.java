package com.bzfar.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 刘成
 * @date 2021-5-28
 */
//@Configuration
//@ConfigurationProperties(prefix = "minio", ignoreUnknownFields = true)
//@Data
public class MinioConfig {

    private String bucket;

    private String host;

    private String url;

    private String accessKey;

    private String secretKey;
}
