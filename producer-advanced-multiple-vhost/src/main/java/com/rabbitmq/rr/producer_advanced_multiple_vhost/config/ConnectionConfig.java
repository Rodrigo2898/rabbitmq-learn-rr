package com.rabbitmq.rr.producer_advanced_multiple_vhost.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "spring.rabbitmq")
public class ConnectionConfig {

    private final String host;
    private final Integer port;
    private final String username;
    private final String password;
    private final String virtualHost;
    private final String alternativeHost;
    private final Integer alternativePort;
    private final String alternativeUsername;
    private final String alternativePassword;
    private final String alternativeVirtualHost;

    @ConstructorBinding
    public ConnectionConfig(String host,
                            Integer port,
                            String username,
                            String password,
                            String virtualHost,
                            String alternativeHost,
                            Integer alternativePort,
                            String alternativeUsername,
                            String alternativePassword,
                            String alternativeVirtualHost) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.virtualHost = virtualHost;
        this.alternativeHost = alternativeHost;
        this.alternativePort = alternativePort;
        this.alternativeUsername = alternativeUsername;
        this.alternativePassword = alternativePassword;
        this.alternativeVirtualHost = alternativeVirtualHost;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public String getAlternativeHost() {
        return alternativeHost;
    }

    public Integer getAlternativePort() {
        return alternativePort;
    }

    public String getAlternativeUsername() {
        return alternativeUsername;
    }

    public String getAlternativePassword() {
        return alternativePassword;
    }

    public String getAlternativeVirtualHost() {
        return alternativeVirtualHost;
    }
}
