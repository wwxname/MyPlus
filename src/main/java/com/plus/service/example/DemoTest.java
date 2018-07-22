package com.plus.service.example;


import com.plus.service.ServiceContainer;
import com.plus.service.annotation.ServletScan;

@ServletScan(value = "com.example.demo.service.example.servlet")
public class DemoTest {
    public static  void  main(String args[]){
        ServiceContainer container =new ServiceContainer(8082);
        container.run(DemoTest.class);
    }
}
