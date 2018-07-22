package com.plus.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author plus me
 * @date 2018/6/13
 */
public abstract class BaseCache<K extends String, V extends Object> implements Cache<K, V> {

    private LoadingCache<K, V> cache;

    public BaseCache() {
        cache = CacheBuilder.newBuilder()
                .maximumSize(10000)
                .build(new CacheLoader<K, V>() {
                    @Override
                    public V load(K k) throws Exception {
                        return loadData(k);
                    }
                });
    }

    /**
     * 超时缓存：数据写入缓存超过一定时间自动刷新
     *
     * @param duration
     * @param timeUtil
     */
    public BaseCache(long duration, TimeUnit timeUtil) {
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(duration, timeUtil)
                .build(new CacheLoader<K, V>() {
                    @Override
                    public V load(K k) throws Exception {
                        return loadData(k);
                    }
                });
    }

    /**
     * 限容缓存：缓存数据个数不能超过maxSize
     *
     * @param maxSize
     */
    public BaseCache(long maxSize) {
        cache = CacheBuilder.newBuilder()
                .maximumSize(maxSize)
                .build(new CacheLoader<K, V>() {
                    @Override
                    public V load(K k) throws Exception {
                        return loadData(k);
                    }
                });
    }

    /**
     * 权重缓存：缓存数据权重和不能超过maxWeight
     *
     * @param maxWeight
     * @param weigher：权重函数类，需要实现计算元素权重的函数
     */
    public BaseCache(long maxWeight, Weigher<K, V> weigher) {
        cache = CacheBuilder.newBuilder()
                .maximumWeight(maxWeight)
                .weigher(weigher)
                .build(new CacheLoader<K, V>() {
                    @Override
                    public V load(K k) throws Exception {
                        return loadData(k);
                    }
                });
    }


    /**
     * 缓存数据加载方法
     *
     * @param key
     * @return
     * @author coshaho
     */
    protected abstract V loadData(K key);

    /**
     * 从缓存获取数据
     *
     * @param param
     * @return
     * @author coshaho
     */
    public V getCache(K param) {
        return cache.getUnchecked(param);
    }
    @Override
    public V get(K k) throws ExecutionException {
        return cache.get(k);
    }

    /**
     * 清除缓存数据，缓存清除后，数据会重新调用load方法获取
     *
     * @param key
     * @author coshaho
     */
    public void refresh(K key) {
        cache.refresh(key);
    }

    /**
     * 主动设置缓存数据
     *
     * @param key
     * @param value
     * @author coshaho
     */
    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }
}
