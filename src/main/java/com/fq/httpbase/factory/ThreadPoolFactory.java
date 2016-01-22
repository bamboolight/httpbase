package com.fq.httpbase.factory;

import java.util.concurrent.*;

/**
 * @author jifang
 * @since 16/1/21下午10:59.
 */
public class ThreadPoolFactory {

    private static final int DEFAULT_CORE_POOL_SIZE = 5;
    private static final int DEFAULT_MAX_POOL_SIZE = 20;
    private static final long DEFAULT_ALIVE_TIME = 20L;
    private static final BlockingQueue<Runnable> DEFAULT_BLOCKING_QUEUE
            = new ArrayBlockingQueue<>(DEFAULT_CORE_POOL_SIZE);

    public static ExecutorService getDefaultPool() {
        return new ThreadPoolExecutor(DEFAULT_CORE_POOL_SIZE, DEFAULT_MAX_POOL_SIZE,
                DEFAULT_ALIVE_TIME, TimeUnit.SECONDS, DEFAULT_BLOCKING_QUEUE);
    }
}
