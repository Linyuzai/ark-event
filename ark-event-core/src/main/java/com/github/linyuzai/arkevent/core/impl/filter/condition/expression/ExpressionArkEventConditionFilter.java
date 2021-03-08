package com.github.linyuzai.arkevent.core.impl.filter.condition.expression;

import com.github.linyuzai.arkevent.core.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.util.HashMap;
import java.util.Map;

public class ExpressionArkEventConditionFilter implements ArkEventConditionFilter {

    private Expression expression;

    public ExpressionArkEventConditionFilter(String eps) {
        this.expression = AviatorEvaluator.compile(eps, true);
    }

    @Override
    public boolean filter(ArkEventSubscriber subscriber, Object event, Map<Object, Object> args) {
        Map<String, Object> env = new HashMap<>();
        env.put("$event", event);
        env.put("$args", args);
        Object v = expression.execute(env);
        if (v instanceof Boolean) {
            return (Boolean) v;
        } else if (v instanceof Long) {
            return (Long) v != 0;
        } else if (v instanceof Double) {
            return ((Double) v).longValue() != 0;
        } else {
            return false;
        }
    }

    @Override
    public int order() {
        return CONDITION_FILTER_EXPRESSION;
    }
}
