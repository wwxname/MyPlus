package com.plus.service.example.servlet;


import com.plus.service.AbstractServlet;
import com.plus.service.annotation.Method;
import com.plus.service.annotation.Path;
import com.plus.service.annotation.ServletBean;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;

@ServletBean
@Path(value = "/test")
@Method(value = "GET")
public class DemoServlet extends AbstractServlet {

    public DemoServlet(){

    }
    public DemoServlet(String path, HttpMethod method) {
        super(path, method);
    }

    @Override
    public Object service(FullHttpRequest request) {
        return "wwx";
    }
}
