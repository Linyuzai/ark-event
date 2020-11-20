package com.github.linyuzai.arkevent.support.condition.type;

import com.github.linyuzai.arkevent.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.ArkEventSubscriber;

public class TypeArkEventConditionFilterFactory implements ArkEventConditionFilter.Factory {

    @Override
    public ArkEventConditionFilter getConditionFilter(ArkEventSubscriber subscriber) {
        if (subscriber instanceof ArkEventType) {
            ArkEventType arkEventType = (ArkEventType) subscriber;
            Class<?>[] classes = arkEventType.arkEventTypes();
            boolean inherited = arkEventType.inherited();
            return new TypeArkEventConditionFilter(classes, inherited);
        }
        OnArkEventType onArkEventType = subscriber.getClass().getAnnotation(OnArkEventType.class);
        if (onArkEventType != null) {
            Class<?>[] classes = onArkEventType.value();
            boolean inherited = onArkEventType.inherited();
            return new TypeArkEventConditionFilter(classes, inherited);
        }
        return null;
    }
}
