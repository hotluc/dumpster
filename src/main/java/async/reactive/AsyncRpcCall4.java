package async.reactive;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class AsyncRpcCall4 {
    public static String rpcCall(String ip,String param) {
        // 模拟远程调用
        System.out.println(ip+" rpcCall "+param);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return param;
    }
    public static void main(String[] args) throws InterruptedException {
        List<String> ips = new ArrayList<>();
        // 模拟10个ip
        for (int i = 1; i <= 10; i++) {
            ips.add("192.168.1." + i);
        }
        long start = System.currentTimeMillis();
        Flowable.fromArray(ips.toArray(new String[0])).observeOn(Schedulers.io()).map(v->rpcCall(v,v)).subscribe(System.out::println,Throwable::printStackTrace);
        System.out.println("main execute over and wait");
        Thread.currentThread().join();
        //System.out.println("总耗时："+(System.currentTimeMillis()-start)+"ms");
    }
}
