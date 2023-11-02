package com.ryu.java.extract.h2.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("database.datasource")
public class DatabaseConfig {
    private String url;
    private String driverClassName;
    private String username;
    private String password;
}
