package com.plus.schedule;

import com.google.common.util.concurrent.AbstractScheduledService;

import java.util.concurrent.TimeUnit;

public class SchduledService extends AbstractScheduledService {
    int i = 0;

    @Override
    protected void runOneIteration() throws Exception {
        if (i == 10) {
            this.stopAsync();
        }
        System.err.println(Thread.currentThread().getName() + "--" + Thread.currentThread().getId() + "--" + this.state().toString() + i);
        i++;
    }

    @Override
    protected Scheduler scheduler() {
        return Scheduler.newFixedDelaySchedule(1, 1, TimeUnit.SECONDS);
    }
}
