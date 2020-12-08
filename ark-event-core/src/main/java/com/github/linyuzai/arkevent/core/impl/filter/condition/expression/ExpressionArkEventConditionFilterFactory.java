package com.github.linyuzai.arkevent.core.impl.filter.condition.expression;

import com.github.linyuzai.arkevent.core.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;

public class ExpressionArkEventConditionFilterFactory implements ArkEventConditionFilter.Factory {

    @Override
    public ArkEventConditionFilter getConditionFilter(ArkEventSubscriber subscriber) {
        OnEventExpression onEventExpression = subscriber.getClass().getAnnotation(OnEventExpression.class);
        if (onEventExpression != null) {
            String expression = onEventExpression.value();
            return new ExpressionArkEventConditionFilter(expression);
        }
        return null;
    }
}
