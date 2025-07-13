package async;

import java.util.concurrent.*;

public class SyncExample {
    //自定义线程池
    private final static int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(
            AVAILABLE_PROCESSORS,
            AVAILABLE_PROCESSORS * 2,
            1,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );
    public void doSomethingA() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("doSomething");
    }
    public void doSomethingB() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("doSomething");
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        POOL_EXECUTOR.execute(() -> {
            SyncExample example = new SyncExample();
            example.doSomethingA();
        });

    }
}
