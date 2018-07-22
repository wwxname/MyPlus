package com.plus.cache;

import java.util.concurrent.ExecutionException;

/**
 * @author plus me
 * @date 2018/6/13
 */
public interface Cache<K,V> {
    /**
     *
     * @param key
     * @return
     */
    V get(K key) throws ExecutionException;

    /**
     *
     * @param key
     * @param value
     */
    void put(K key, V value);
}
