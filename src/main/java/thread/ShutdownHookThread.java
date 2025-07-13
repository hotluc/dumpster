package thread;

public class ShutdownHookThread extends Thread{
    @Override
    public void run() {
        System.out.println("JVM关闭时执行的线程");
    }
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new ShutdownHookThread());
        System.out.println("JVM关闭时执行的线程");
    }
}
