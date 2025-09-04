package com.zh8888.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secretKey; // 密钥(Base64编码，至少32字节)
    private Long expiration; // 过期时间(毫秒)
}