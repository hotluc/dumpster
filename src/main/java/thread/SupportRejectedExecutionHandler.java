package thread;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public interface SupportRejectedExecutionHandler extends RejectedExecutionHandler {
    default void beforeReject(Runnable r, ThreadPoolExecutor executor) {
        if (executor instanceof SupportThreadPoolExecutor) {
            ((SupportThreadPoolExecutor) executor).incrementRejectCount();
        }
        System.out.println("线程池已满，任务被拒绝");

    }
}
