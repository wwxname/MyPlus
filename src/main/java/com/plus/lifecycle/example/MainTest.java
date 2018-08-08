package com.plus.lifecycle.example;

/**
 * @author plus me
 * @date 2018/8/8
 */
public class MainTest {
    public static void main(String args[]) {
        DemoLifeProcessor demoLifeProcessor = new DemoLifeProcessor();
        demoLifeProcessor.init();
        demoLifeProcessor.start();
    }
}
