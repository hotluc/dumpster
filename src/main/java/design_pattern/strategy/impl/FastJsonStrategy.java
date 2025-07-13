package design_pattern.strategy.impl;

import design_pattern.strategy.inter.JsonStrategy;

public class FastJsonStrategy implements JsonStrategy {
    @Override
    public String toJson() {
        System.out.println("FastJson");
        return "FastJson";
    }

    @Override
    public Object fromJson(String json) {
        return null;
    }
}
