package design_pattern.strategy;

import design_pattern.strategy.impl.FastJsonStrategy;
import design_pattern.strategy.impl.GsonStrategy;
import design_pattern.strategy.impl.JacksonStrategy;
import design_pattern.strategy.inter.JsonStrategy;

import java.util.Map;

/**
 *
 */
public class JsonContext {
        private static final Map<JsonType, JsonStrategy> STRATEGY_MAP = Map.of(
            JsonType.GSON, new GsonStrategy(),
            JsonType.JACKSON, new JacksonStrategy(),
            JsonType.FASTJSON, new FastJsonStrategy()
    );
    public static JsonStrategy getStrategy(JsonType type) {
        return STRATEGY_MAP.get(type);
    }


}
