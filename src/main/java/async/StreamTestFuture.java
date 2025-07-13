package async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class StreamTestFuture {
    public static String rpcCall(String ip,String param) {
        // 模拟远程调用
        System.out.println(ip+"rpcCall"+param);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return param;
    }
    public static void main(String[] args) {
        List<String> ips = new ArrayList<>();
        // 模拟10个ip
        for (int i = 1; i <= 10; i++) {
            ips.add("192.168.1." + i);
        }
        // 模拟10个参数
        long start = System.currentTimeMillis();
        List<CompletableFuture<String>> futureList = ips.stream().map(ip -> CompletableFuture.supplyAsync(() -> rpcCall(ip, ip))).toList();
        futureList.stream().forEach(r->{
            try {
                System.out.println(r.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("cost: " + (System.currentTimeMillis() - start) + "ms");
    }
}
