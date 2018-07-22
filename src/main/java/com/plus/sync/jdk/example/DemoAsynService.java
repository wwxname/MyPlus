package com.plus.sync.jdk.example;


import com.plus.sync.jdk.AsynServiceFactory;

public class DemoAsynService extends AsynServiceFactory<DemoAsynServiceInter> implements DemoAsynServiceInter {

    @Override
    public  void  test(){
        System.err.println("llll");
    }
    public static  void main(String args[]) throws Exception {
       DemoAsynServiceInter asynListenerService = new DemoAsynService().createAsynService();
       asynListenerService.test();
        asynListenerService.test();
        asynListenerService.test();
        asynListenerService.test();


    }



}
