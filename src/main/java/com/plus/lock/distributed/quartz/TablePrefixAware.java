package com.plus.lock.distributed.quartz;

public interface TablePrefixAware {
    void setTablePrefix(String tablePrefix);
    void setSchedName(String schedName);
}
