package com.yang.myweb.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能描述 WebConfig
 *
 * @author Daze
 * @date 2021-01-13
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "conf")
public class WebConfig {
    private String salt;
}
