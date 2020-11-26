package com.github.linyuzai.arkevent.support.filter.condition.type;

import com.github.linyuzai.arkevent.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.ArkEventSubscriber;

public class TypeArkEventConditionFilterFactory implements ArkEventConditionFilter.Factory {

    @Override
    public ArkEventConditionFilter getConditionFilter(ArkEventSubscriber subscriber) {
        OnEventType onEventType = subscriber.getClass().getAnnotation(OnEventType.class);
        if (onEventType != null) {
            Class<?>[] classes = onEventType.value();
            boolean inherited = onEventType.inherited();
            return new TypeArkEventConditionFilter(classes, inherited);
        }
        return null;
    }
}
