package com.plus.sync.cglib.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainTest {

    public static  void main(String args[]){
       DemoService demoService =   new DemoService().getAsynService();
       demoService.test();
        demoService.test();

    }
}
