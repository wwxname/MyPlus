package com.plus.sync.jdk.example;

public class Test {
    public static  void main(String args[]) throws Exception {

        DemoAsynServiceInter asynListenerService = new DemoAsynService().createAsynService();
        asynListenerService.test();
        asynListenerService.test();
        asynListenerService.test();
        asynListenerService.test();

    }
}
