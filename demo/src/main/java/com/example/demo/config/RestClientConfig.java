package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.ConnectionKeepAliveStrategy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.util.TimeValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {
    private final WebProps webProps;

    @Bean
    public RestClient restClient() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(webProps.getMaxTotal());
        connectionManager.setDefaultMaxPerRoute(webProps.getDefaultMaxPerRoute());

        ConnectionKeepAliveStrategy keepAliveStrategy = (HttpResponse response, HttpContext context) -> {
            return TimeValue.ofMilliseconds(webProps.getKeepAliveTimeMillis());
        };

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(keepAliveStrategy)
                .evictIdleConnections(TimeValue.ofMilliseconds(webProps.getEvictIdleConnectionTimeMillis()))
                .build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        factory.setConnectTimeout(Duration.ofMillis(webProps.getConnectTimeoutMillis()));
        factory.setConnectionRequestTimeout(Duration.ofMillis(webProps.getConnectionRequestTimeoutMillis()));
        factory.setReadTimeout(Duration.ofMillis(webProps.getReadTimeoutMillis()));

        return RestClient.builder().requestFactory(factory).baseUrl(webProps.getBaseUrl()).build();
    }
}
