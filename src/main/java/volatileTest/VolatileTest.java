package volatileTest;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest {
    public static AtomicInteger counter = new AtomicInteger(0);
    public static void increment() {

    }
    private static int THREAD_COUNT = 20;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    increment();
                }
            });
            threads[i].start();
        }
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i].join();  // 等待每个线程执行完成
        }
        System.out.println(counter);

    }
}
