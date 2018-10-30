package com.plus.lock.distributed.redis.example;

import com.plus.lock.distributed.redis.RedisLockService;
import redis.clients.jedis.JedisPool;

class T1 extends Thread {
    RedisLockService service;

    public T1(RedisLockService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.waitForLock("wwx");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //service.unlock("wwx");
        System.err.println(Thread.currentThread().getName() + "----T1");
    }
}

class T2 extends Thread {
    RedisLockService service;

    public T2(RedisLockService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.waitForLock("wwx");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println(Thread.currentThread().getName() + "----T2");
    }
}

public class MainTest {

    public static void main(String args[]) throws InterruptedException {
        JedisPool pool = new JedisPool("192.168.1.87 ", 8585);
        RedisLockService service = new RedisLockService(pool);
        //
        // new T1(service).start();
        //Thread.sleep(2000);
        //new T2(service).start();

    }
}
