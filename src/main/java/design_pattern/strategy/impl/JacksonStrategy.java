package design_pattern.strategy.impl;

import design_pattern.strategy.inter.JsonStrategy;

public class JacksonStrategy implements JsonStrategy {
    @Override
    public String toJson() {
        System.out.println("Jackson");
        return "Jackson";
    }

    @Override
    public Object fromJson(String json) {
        return null;
    }
}
