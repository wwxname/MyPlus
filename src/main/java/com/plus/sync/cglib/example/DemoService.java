package com.plus.sync.cglib.example;

import com.plus.sync.cglib.AsynServiceFactory;

public class DemoService extends AsynServiceFactory<DemoService> {
    public void test(){
        System.err.println("test");
    }
}
