package com.bzfar.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 刘成
 * @date 2021-6-3
 */
@Configuration
@ConfigurationProperties(prefix = "tongdahai" , ignoreInvalidFields = true)
@Data
public class TongDaHaiConfiguration {

    /**
     * 用户名
     */
    private String userId;

    /**
     * 密码
     */
    private String passWord;
}
