package thread;

import java.util.concurrent.ThreadPoolExecutor;

public class SupportAbortPolicyRejected extends ThreadPoolExecutor.AbortPolicy implements SupportRejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        beforeReject(r, e);
        super.rejectedExecution(r, e);
    }
}
