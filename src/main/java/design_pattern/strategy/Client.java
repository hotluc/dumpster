package design_pattern.strategy;

import design_pattern.strategy.inter.JsonStrategy;

public class Client {
    public static void main(String[] args) {
        JsonStrategy jsonStrategy = JsonContext.getStrategy(JsonType.FASTJSON);
        jsonStrategy.toJson();
    }
}
