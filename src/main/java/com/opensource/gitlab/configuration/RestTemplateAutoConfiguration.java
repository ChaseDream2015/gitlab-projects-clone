package com.opensource.gitlab.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 初始化RestTemplate配置类
 * Created by sunquan on 2019/10/24.
 */
@EnableConfigurationProperties(RestProperties.class)
@Configuration
public class RestTemplateAutoConfiguration {
    @Autowired
    private RestProperties restProperties;

    @ConditionalOnMissingBean(RestTemplate.class)
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(httpRequestFactory());
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory httpRequestFactory() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(restProperties.getConnectionRequestTimeout());
        httpRequestFactory.setConnectTimeout(restProperties.getConnectTimeout());
        httpRequestFactory.setReadTimeout(restProperties.getReadTimeout());
        return httpRequestFactory;
    }
}
