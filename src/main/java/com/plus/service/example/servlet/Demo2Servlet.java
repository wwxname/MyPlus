package com.plus.service.example.servlet;


import com.plus.service.AbstractServlet;
import com.plus.service.annotation.Method;
import com.plus.service.annotation.Path;
import com.plus.service.annotation.ServletBean;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;

@ServletBean
@Path(value = "/test2")
@Method(value = "GET")
public class Demo2Servlet extends AbstractServlet {
    public Demo2Servlet(){

    }
    public Demo2Servlet(String path, HttpMethod method) {
        super(path, method);
    }

    @Override
    public Object service(FullHttpRequest request) {
        return new DemoServlet("/23",null);
    }
}
