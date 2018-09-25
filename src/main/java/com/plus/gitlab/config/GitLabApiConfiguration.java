package com.plus.gitlab.config;

import com.plus.gitlab.GitLabApi;
import com.plus.gitlab.util.GitLabApiBuilder;
import com.plus.gitlab.util.TokenType;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

/**
 * @author dongdong
 * @date 2018/5/21
 */
@Configuration
public class GitLabApiConfiguration {

    @Autowired
    GitLabApiProperties gitLabApiProperties;

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(HttpClients.createDefault());
        factory.setConnectTimeout(gitLabApiProperties.getConnectTimeout());
        factory.setConnectionRequestTimeout(gitLabApiProperties.getConnectionRequestTimeout());
        factory.setReadTimeout(gitLabApiProperties.getReadTimeout());
        return factory;
    }

    @Bean
    public GitLabApi getGitLabApi() {
        return new GitLabApiBuilder().host(gitLabApiProperties.getHost())
                .token(gitLabApiProperties.getToken())
                .tokenType(TokenType.ACCESS_TOKEN)
                .clientHttpRequestFactory(clientHttpRequestFactory())
                .build();
    }

}
