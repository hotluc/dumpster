package thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SupportThreadPoolExecutor extends ThreadPoolExecutor {
    private final AtomicInteger rejectCount = new AtomicInteger();

    public SupportThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public void incrementRejectCount() {
        rejectCount.incrementAndGet();
    }

    public int getRejectCount() {
        return rejectCount.get();
    }
}
