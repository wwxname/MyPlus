package com.plus.lifecycle;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author plus me
 * @date 2018/8/8
 */
public abstract class AbstractDefaultLifecycleProcessor implements LifecycleProcessor {

    private final Log logger = LogFactory.getLog(getClass());

    private volatile long timeoutPerShutdownPhase = 30000;

    private volatile boolean running;

    /**
     * 为关闭的时间指定以毫秒为间隔的最长时间任何阶段
     *
     * @param timeoutPerShutdownPhase
     */
    public void setTimeoutPerShutdownPhase(long timeoutPerShutdownPhase) {
        this.timeoutPerShutdownPhase = timeoutPerShutdownPhase;
    }

    /**
     * 自己实现
     *
     * @return
     */
    public abstract Map<String, Lifecycle> getLifecycleBeans();

    /**
     * 确定给定bean的生命周期阶段
     *
     * @param bean the bean to introspect
     * @return the phase an integer value. The suggested default is 0.
     * @see Phased
     * @see SmartLifecycle
     */
    protected int getPhase(Lifecycle bean) {
        return (bean instanceof Phased ? ((Phased) bean).getPhase() : 0);
    }

    @Override
    public void onRefresh() {
        startBeans(true);
        this.running = true;
    }

    @Override
    public void onClose() {
        stopBeans();
        this.running = false;
    }

    @Override
    public void init() {
        this.setTimeoutPerShutdownPhase(30000);
    }

    @Override
    public void start() {
        startBeans(false);
        this.running = true;
    }

    @Override
    public void stop() {
        stopBeans();
        this.running = false;
    }

    @Override
    public void destory() {

    }


    // Internal helpers

    private void startBeans(boolean autoStartupOnly) {
        Map<String, Lifecycle> lifecycleBeans = getLifecycleBeans();
        Map<Integer, LifecycleGroup> phases = new HashMap<>();
        lifecycleBeans.forEach((beanName, bean) -> {
            if (!autoStartupOnly || (bean instanceof SmartLifecycle && ((SmartLifecycle) bean).isAutoStartup())) {
                int phase = getPhase(bean);
                LifecycleGroup group = phases.get(phase);
                if (group == null) {
                    group = new LifecycleGroup(phase, this.timeoutPerShutdownPhase, lifecycleBeans, autoStartupOnly);
                    phases.put(phase, group);
                }
                group.add(beanName, bean);
            }
        });
        if (!phases.isEmpty()) {
            List<Integer> keys = new ArrayList<>(phases.keySet());
            Collections.sort(keys);
            for (Integer key : keys) {
                phases.get(key).start();
            }
        }
    }

    /**
     * Start the specified bean as part of the given set of Lifecycle beans,
     * making sure that any beans that it depends on are started first.
     *
     * @param lifecycleBeans Map with bean name as key and Lifecycle instance as value
     * @param beanName       the name of the bean to start
     */
    private void doStart(Map<String, ? extends Lifecycle> lifecycleBeans, String beanName, boolean autoStartupOnly) {
        Lifecycle bean = lifecycleBeans.remove(beanName);
        if (bean != null && !this.equals(bean)) {
            bean.init();
            if (!bean.isRunning() &&
                    (!autoStartupOnly || !(bean instanceof SmartLifecycle) || ((SmartLifecycle) bean).isAutoStartup())) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Starting bean '" + beanName + "' of type [" + bean.getClass() + "]");
                }
                try {
                    bean.start();
                } catch (Throwable ex) {
                    throw new RuntimeException("Failed to start bean '" + beanName + "'", ex);
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("Successfully started bean '" + beanName + "'");
                }
            }
        }
    }

    private void stopBeans() {
        Map<String, Lifecycle> lifecycleBeans = getLifecycleBeans();
        Map<Integer, LifecycleGroup> phases = new HashMap<>();
        lifecycleBeans.forEach((beanName, bean) -> {
            int shutdownOrder = getPhase(bean);
            LifecycleGroup group = phases.get(shutdownOrder);
            if (group == null) {
                group = new LifecycleGroup(shutdownOrder, this.timeoutPerShutdownPhase, lifecycleBeans, false);
                phases.put(shutdownOrder, group);
            }
            group.add(beanName, bean);
        });
        if (!phases.isEmpty()) {
            List<Integer> keys = new ArrayList<>(phases.keySet());
            keys.sort(Collections.reverseOrder());
            for (Integer key : keys) {
                phases.get(key).stop();
            }
        }
    }

    /**
     * Stop the specified bean as part of the given set of Lifecycle beans,
     * making sure that any beans that depends on it are stopped first.
     *
     * @param lifecycleBeans Map with bean name as key and Lifecycle instance as value
     * @param beanName       the name of the bean to stop
     */
    private void doStop(Map<String, ? extends Lifecycle> lifecycleBeans, final String beanName,
                        final CountDownLatch latch, final Set<String> countDownBeanNames) {

        Lifecycle bean = lifecycleBeans.remove(beanName);
        if (bean != null) {
            bean.init();
            try {
                if (bean.isRunning()) {
                    if (bean instanceof SmartLifecycle) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("Asking bean '" + beanName + "' of type [" + bean.getClass() + "] to stop");
                        }
                        countDownBeanNames.add(beanName);
                        ((SmartLifecycle) bean).stop(() -> {
                            latch.countDown();
                            countDownBeanNames.remove(beanName);
                            if (logger.isDebugEnabled()) {
                                logger.debug("Bean '" + beanName + "' completed its stop procedure");
                            }
                        });
                    } else {
                        if (logger.isDebugEnabled()) {
                            logger.debug("Stopping bean '" + beanName + "' of type [" + bean.getClass() + "]");
                        }
                        bean.stop();
                        if (logger.isDebugEnabled()) {
                            logger.debug("Successfully stopped bean '" + beanName + "'");
                        }
                    }
                } else if (bean instanceof SmartLifecycle) {
                    // don't wait for beans that aren't running
                    latch.countDown();
                }
            } catch (Throwable ex) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Failed to stop bean '" + beanName + "'", ex);
                }
            }
        }
    }

    private class LifecycleGroup {

        private final List<LifecycleGroupMember> members = new ArrayList<>();

        private final int phase;

        private final long timeout;

        private final Map<String, ? extends Lifecycle> lifecycleBeans;

        private final boolean autoStartupOnly;

        private volatile int smartMemberCount;

        public LifecycleGroup(int phase, long timeout, Map<String, ? extends Lifecycle> lifecycleBeans, boolean autoStartupOnly) {
            this.phase = phase;
            this.timeout = timeout;
            this.lifecycleBeans = lifecycleBeans;
            this.autoStartupOnly = autoStartupOnly;
        }

        public void add(String name, Lifecycle bean) {
            if (bean instanceof SmartLifecycle) {
                this.smartMemberCount++;
            }
            this.members.add(new LifecycleGroupMember(name, bean));
        }

        public void start() {
            if (this.members.isEmpty()) {
                return;
            }
            if (logger.isInfoEnabled()) {
                logger.info("Starting beans in phase " + this.phase);
            }
            Collections.sort(this.members);
            for (LifecycleGroupMember member : this.members) {
                if (this.lifecycleBeans.containsKey(member.name)) {
                    doStart(this.lifecycleBeans, member.name, this.autoStartupOnly);
                }
            }
        }

        public void stop() {
            if (this.members.isEmpty()) {
                return;
            }
            if (logger.isInfoEnabled()) {
                logger.info("Stopping beans in phase " + this.phase);
            }
            this.members.sort(Collections.reverseOrder());
            CountDownLatch latch = new CountDownLatch(this.smartMemberCount);
            Set<String> countDownBeanNames = Collections.synchronizedSet(new LinkedHashSet<>());
            for (LifecycleGroupMember member : this.members) {
                if (this.lifecycleBeans.containsKey(member.name)) {
                    doStop(this.lifecycleBeans, member.name, latch, countDownBeanNames);
                } else if (member.bean instanceof SmartLifecycle) {
                    // already removed, must have been a dependent
                    latch.countDown();
                }
            }
            try {
                latch.await(this.timeout, TimeUnit.MILLISECONDS);
                if (latch.getCount() > 0 && !countDownBeanNames.isEmpty() && logger.isWarnEnabled()) {
                    logger.warn("Failed to shut down " + countDownBeanNames.size() + " bean" +
                            (countDownBeanNames.size() > 1 ? "s" : "") + " with phase value " +
                            this.phase + " within timeout of " + this.timeout + ": " + countDownBeanNames);
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }


    /**
     * Adapts the Comparable interface onto the lifecycle phase model.
     */
    private class LifecycleGroupMember implements Comparable<LifecycleGroupMember> {

        private final String name;

        private final Lifecycle bean;

        LifecycleGroupMember(String name, Lifecycle bean) {
            this.name = name;
            this.bean = bean;
        }

        @Override
        public int compareTo(LifecycleGroupMember other) {
            int thisOrder = getPhase(this.bean);
            int otherOrder = getPhase(other.bean);
            return Integer.compare(thisOrder, otherOrder);
        }
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }
}
