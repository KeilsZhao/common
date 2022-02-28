package com.bzfar.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 刘成
 * @date 2021-6-3
 */
@Configuration
@ConfigurationProperties(prefix = "mock" , ignoreInvalidFields = true)
@Data
public class ScoringConfiguration {

    /**
     * 是否走mock数据
     */
    private boolean scoringMock;
}
