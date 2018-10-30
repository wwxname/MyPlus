package com.plus.lock.distributed.quartz;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author plus me
 * @date 2018/10/19
 */
@Service
public class DBLockService extends PlusProcessExecuteSemaphoreSupport {

    public boolean deployObtainLock(String lockName) {
        boolean transOwner = false;
        Connection conn = null;

        if (lockName != null) {
            // If we aren't using db locks, then delay getting DB connection
            // until after acquiring the lock since it isn't needed.
            if (getLockHandler().requiresConnection()) {
                conn = getNonManagedTXConnection();
            }

            transOwner = getLockHandler().obtainLock(conn, lockName);
        }
        return false;

    }

    public boolean deployReleaseLock(String lockName) {
        return true;
    }

    public static void main(String[] args) {

    }
}
