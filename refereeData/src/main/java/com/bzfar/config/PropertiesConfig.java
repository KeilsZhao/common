package com.bzfar.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "luence")
@Component
@Data
public class PropertiesConfig {

    /**
     * 裁判文书luence地址
     */
    private String pathUrl;

    /**
     * 导诉咨询luence路径
     */
    private String guidanceUrl;

}
