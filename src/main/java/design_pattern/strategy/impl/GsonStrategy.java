package design_pattern.strategy.impl;

import design_pattern.strategy.inter.JsonStrategy;

public class GsonStrategy implements JsonStrategy {
    @Override
    public String toJson() {
        System.out.println("Gson");
        return "Gson";
    }

    @Override
    public Object fromJson(String json) {
        return null;
    }
}
