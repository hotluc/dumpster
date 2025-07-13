package design_pattern.strategy.instance;

/**
 * 懒汉式单例的改进
 */
public class LazySingleton1 {
    private volatile static LazySingleton1 instance;

    public static LazySingleton1 getInstance() {
        if (instance == null) {
            synchronized (LazySingleton1.class) {
                if (instance == null) {
                    instance = new LazySingleton1();
                }
            }
        }
        return instance;
    }
}
