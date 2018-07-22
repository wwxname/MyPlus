package com.plus.cache.example;


import com.plus.cache.BaseCache;

/**
 * @author plus me
 * @date 2018/6/13
 */
public class DemoCache extends BaseCache {
    @Override
    protected Object loadData(String key) {
        Object o = loadDataFromDisk(key);
        this.put(key, o);
        return o;
    }

    /**
     * 定义数据的真实来自哪里
     * @param key
     * @return
     */
    private Object loadDataFromDisk(String key) {
        return new Object();
    }
}
