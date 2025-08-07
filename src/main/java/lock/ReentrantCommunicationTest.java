package lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class ReentrantCommunicationTest {
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    static class WaitTarget implements Runnable {
        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println("我是等待方");
                condition.await();
                System.out.println("收到通知，等待方继续执行");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finally {
                lock.unlock();
            }
        }
    }
    static class NotifyTarget implements Runnable{

        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println("我是通知方");
                condition.signal();
                System.out.println("发出通知了，但是线程还没有立马释放锁");
            }
            finally {
                lock.unlock();
            }
        }
    }

    public static void test(){
        log.error("start to execute unfinish operate error, id is {}, error is {}", System.getenv("GoLand"), new RuntimeException().getMessage());

    }
    public static void main(String[] args) {
        test();
    }


}
