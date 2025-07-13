package thread;

import com.sun.nio.sctp.ShutdownNotification;

import java.nio.Buffer;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        SupportThreadPoolExecutor executor = new SupportThreadPoolExecutor(1, 1, 1024, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1),new SupportAbortPolicyRejected());
        for (int i = 0; i < 3; i++) {
            try {
                executor.execute(() -> {
                    try {
                        Thread.sleep(Integer.MAX_VALUE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(1000);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5,10,60L,TimeUnit.SECONDS,new ArrayBlockingQueue<>(100));
        pool.prestartAllCoreThreads();

        System.out.println("拒绝任务数：" + executor.getRejectCount());
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("关闭线程池");
            executor.shutdown();
            scheduledExecutorService.shutdown();
            ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
            lock.writeLock().lock();
            pool.shutdown();
        }));
    }
}
