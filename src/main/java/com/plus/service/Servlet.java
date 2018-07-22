package com.plus.service;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;

public interface Servlet {
    Object service(FullHttpRequest request);

    String getPath();

    void setPath(String path);

    HttpMethod getMethod();

    void setHttpMethod(HttpMethod method);
}
