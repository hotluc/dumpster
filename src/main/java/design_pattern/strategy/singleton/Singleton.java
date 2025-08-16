package design_pattern.strategy.singleton;

import java.util.concurrent.atomic.AtomicInteger;

public class Singleton {
    public static final Singleton INSTANCE = new Singleton();
    private Singleton() {
    }

    
}
