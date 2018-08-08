package com.plus.lifecycle.example;

import com.plus.lifecycle.AbstractDefaultLifecycleProcessor;
import com.plus.lifecycle.Lifecycle;

import java.util.HashMap;
import java.util.Map;

/**
 * @author plus me
 * @date 2018/8/8
 */
public class DemoLifeProcessor extends AbstractDefaultLifecycleProcessor {
    @Override
    public Map<String, Lifecycle> getLifecycleBeans() {
        Map map = new HashMap();
        map.put("demo", new DemoSmartLifecycle());
        map.put("demo1", new Demo1SmartLifecycle());
        return map;
    }
}
