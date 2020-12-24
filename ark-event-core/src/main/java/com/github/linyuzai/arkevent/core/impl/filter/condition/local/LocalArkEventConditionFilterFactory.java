package com.github.linyuzai.arkevent.core.impl.filter.condition.local;

import com.github.linyuzai.arkevent.core.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;

public class LocalArkEventConditionFilterFactory implements ArkEventConditionFilter.Factory {

    @Override
    public ArkEventConditionFilter getConditionFilter(ArkEventSubscriber subscriber) {
        if (subscriber.getClass().isAnnotationPresent(OnLocalEvent.class)) {
            return LocalArkEventConditionFilter.INSTANCE;
        }
        return null;
    }
}
