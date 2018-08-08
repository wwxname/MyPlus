package com.plus.lifecycle.example;

import com.plus.lifecycle.SmartLifecycle;
import com.plus.schedule.SchduledService;

/**
 * @author plus me
 * @date 2018/8/8
 */
public class DemoSmartLifecycle implements SmartLifecycle {

    private SchduledService schduledService;

    private volatile boolean running;

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        stop();
        if (callback != null) {
            callback.run();
        }
    }

    @Override
    public void init() {
        schduledService = new SchduledService();
        this.running = false;
    }

    @Override
    public void start() {
        if (!running) {
            schduledService.startAsync();
            this.running = true;
        }
    }

    @Override
    public void stop() {
        if (running) {
            this.running = false;
            destory();
        }
    }

    @Override
    public void destory() {

    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Override
    public int getPhase() {
        return 2;
    }
}
