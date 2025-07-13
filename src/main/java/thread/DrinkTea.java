package thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 喝茶的异步回调
 */
public class DrinkTea {
    private static int SLEEP_GAP = 3;

    public static void main(String[] args) {
        // 任务 1：洗水壶-> 烧开水
        CompletableFuture<Boolean> hotJob = CompletableFuture.supplyAsync(()->{
            System.out.println("洗好水壶");
            System.out.println("烧开水");
            try {
                TimeUnit.SECONDS.sleep(SLEEP_GAP);
                Thread.sleep(SLEEP_GAP);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("水开了");
            return true;
        });
        // 任务 2：洗茶壶-> 洗茶杯-> 拿茶叶
        CompletableFuture<Boolean> washJob = CompletableFuture.supplyAsync(()->{
            System.out.println("洗茶杯");
            try {
                TimeUnit.SECONDS.sleep(SLEEP_GAP);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("洗完了");
            return true;
        });
        CompletableFuture<String> drinkJob= hotJob.thenCombine(washJob,(hotOK,washOK)->{
            if (hotOK&&washOK){
                System.out.println("泡茶喝，茶喝完");
                return "茶喝完了";
            }
            return "茶没喝到";
        });
        System.out.println(drinkJob.join());
    }
}
