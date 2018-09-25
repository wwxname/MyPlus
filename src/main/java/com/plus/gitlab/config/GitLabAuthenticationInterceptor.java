package com.plus.gitlab.config;


import com.plus.gitlab.util.TokenType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @author dongdong
 * @date 2018/5/21
 */
@Slf4j
public class GitLabAuthenticationInterceptor implements ClientHttpRequestInterceptor {

    private final TokenType tokenType;
    private final String token;

    public GitLabAuthenticationInterceptor(TokenType tokenType, String token) {
        this.tokenType = tokenType;
        this.token = token;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        HttpHeaders headers = httpRequest.getHeaders();
        headers.add(tokenType.getTokenHeaderName(), String.format(tokenType.getTokenHeaderFormat(), token));
        //log.debug("method:" + httpRequest.getMethod().name() + " url: " + httpRequest.getURI());
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
