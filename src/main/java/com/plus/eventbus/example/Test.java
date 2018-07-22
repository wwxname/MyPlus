package com.plus.eventbus.example;

import com.plus.eventbus.GuavaEvent;
import com.plus.eventbus.GuavaEventBus;
import com.plus.eventbus.GuavaListener;

class MyEvent extends GuavaEvent<String> {
    public MyEvent(String s) {
        super(s);
    }
}

class MyListener implements GuavaListener<MyEvent> {
    @Override
    public void lister(MyEvent event) {
        String s = event.getInfo();
        System.err.println(s);
    }
}

class MyListener1 implements GuavaListener<MyEvent>{
    @Override
    public void lister(MyEvent event) {
        String s = event.getInfo();
        System.err.println(s);
    }
}

public class Test {
    public static  void main(String args[]){
        MyListener myListener = new MyListener();
        MyListener1 myListener1 = new MyListener1();
        GuavaEventBus.register(myListener,myListener1);
       // GuavaEventBus.register(myListener1);
        GuavaEventBus.post(new MyEvent("wwx"));

    }
}
