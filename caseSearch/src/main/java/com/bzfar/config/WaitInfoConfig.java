package com.bzfar.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mock", ignoreUnknownFields = true)
@Data
public class WaitInfoConfig {

    /** 是否开启开庭排期mock数据 */
    private boolean waitInfoDebug;

    /**
     * 是否开启案件查询mock数据
     */
    private boolean caseSearchDebug;
}
