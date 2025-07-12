package com.file.share.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "db")
public class DbProperties {
    private String url;
    private String user;
    private String password;
}
