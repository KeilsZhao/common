package com.bzfar.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author:""
 * @since :2021/11/15 14:05
 */
@Configuration
@ConfigurationProperties(prefix = "case.register.ftp",ignoreInvalidFields = true)
@Data
public class FtpConfig {

    /**
     * ip地址
     */
    private String host;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 工作目录
     */
    private String workDir;

    /**
     * 根目录
     */
    private String root;

    /**
     * 线程池最大连接
     */
    private Integer maxTotal;

    /**
     * 最小活跃
     */
    private Integer minIdle;

    /**
     * 最大活跃
     */
    private Integer maxIdle;

    /**
     * 最长等待时间
     */
    private Integer maxWaitMillis;

    /**
     * 返回前端地址
     */
    private String http;
}
