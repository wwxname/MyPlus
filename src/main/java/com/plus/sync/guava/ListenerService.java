package com.plus.sync.guava;

public interface ListenerService<V> {

    public V call() throws Exception;

    public void onSuccess(V result);

    public void onFailure(Throwable thrown);


}
