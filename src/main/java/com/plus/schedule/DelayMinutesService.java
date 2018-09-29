package com.plus.schedule;

import com.google.common.util.concurrent.AbstractScheduledService;

import javax.lang.model.type.NullType;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author plus me
 * @date 2018/9/28
 */
public class DelayMinutesService extends AbstractScheduledService {

    private Executor executor;

    private Integer defaultDalay = 600;

    private Integer minutes;

    private DelayMinutesService() {
    }

    @Override
    protected void runOneIteration() throws Exception {
        executor.execute();
        this.stopAsync();
    }

    public static DelayMinutesService newInstance(Integer minutes, Executor executor) {
        DelayMinutesService service = new DelayMinutesService();
        service.executor = executor;
        service.minutes = minutes;
        return service;
    }

    @Override
    protected Scheduler scheduler() {
        return Scheduler.newFixedDelaySchedule(minutes, defaultDalay, TimeUnit.MINUTES);
    }

}
