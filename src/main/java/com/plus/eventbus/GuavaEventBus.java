package com.plus.eventbus;

import com.google.common.eventbus.AsyncEventBus;

import java.util.concurrent.Executors;

public class GuavaEventBus {
    private static AsyncEventBus asyncEventBus = new AsyncEventBus(Executors.newFixedThreadPool(10));

    public static void register(GuavaListener listener, GuavaListener... guavaListeners) {
        asyncEventBus.register(listener);
        if (guavaListeners.length > 0) {
            for (int i = 0; i < guavaListeners.length; i++) {
                asyncEventBus.register(guavaListeners[i]);
            }
        }
    }

    public static void post(GuavaEvent event) {
        asyncEventBus.post(event);
    }
}
