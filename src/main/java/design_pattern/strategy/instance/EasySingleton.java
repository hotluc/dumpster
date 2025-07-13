package design_pattern.strategy.instance;

public class EasySingleton {
    private static EasySingleton instance = new EasySingleton();

    private EasySingleton() {
    }

    public static EasySingleton getInstance() {
        return instance;
    }
}
