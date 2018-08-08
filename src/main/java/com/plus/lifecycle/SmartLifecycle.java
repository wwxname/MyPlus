package com.plus.lifecycle;

/**
 * @author plus me
 * @date 2018/8/8
 */
public interface SmartLifecycle extends Lifecycle, Phased{
    /**
     * 组件是否自动启动
     *
     * @return
     */
    boolean isAutoStartup();

    /**
     * 组件停止时候回调
     *
     * @param callback
     */
    void stop(Runnable callback);
}
