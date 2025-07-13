package design_pattern.strategy.inter;

/**
 * 抽象策略类
 * 
 */
public interface JsonStrategy {
    String toJson();
    Object fromJson(String json);
}
