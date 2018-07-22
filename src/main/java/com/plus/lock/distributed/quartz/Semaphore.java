package com.plus.lock.distributed.quartz;



import com.plus.lock.LockException;

import java.sql.Connection;

public interface Semaphore {
    boolean obtainLock(Connection conn, String lockName) throws LockException;

    /**
     * Release the lock on the identified resource if it is held by the calling
     * thread.
     */
    void releaseLock(String lockName) throws LockException;

    /**
     * Whether this Semaphore implementation requires a database connection for
     * its lock management operations.
     *
     * @see #obtainLock(Connection, String)
     * @see #releaseLock(String)
     */
    boolean requiresConnection();
}
