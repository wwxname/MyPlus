package com.plus.lifecycle;

/**
 * @author plus me
 * @date 2018/8/8
 */
public interface Lifecycle {
    /**
     * 初始化
     */
    void init();

    /**
     * 开始
     */
    void start();

    /**
     * 结束
     */
    void stop();


    /**
     * 销毁
     */
    void destory();

    /**
     * 是否处于运行的状态
     *
     * @return
     */
    boolean isRunning();
}
