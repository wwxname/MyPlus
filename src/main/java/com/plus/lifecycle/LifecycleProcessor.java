package com.plus.lifecycle;

/**
 * @author plus me
 * @date 2018/8/8
 */
public interface LifecycleProcessor extends Lifecycle {
    /**
     * 上下文刷新的通知，例如自动启动组件
     */
    void onRefresh();

    /**
     * 上下文关闭阶段的通知，例如自动停止组件
     */
    void onClose();
}
