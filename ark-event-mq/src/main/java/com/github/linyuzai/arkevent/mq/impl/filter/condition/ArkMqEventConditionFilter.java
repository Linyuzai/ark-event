package com.github.linyuzai.arkevent.mq.impl.filter.condition;

import com.github.linyuzai.arkevent.support.ArkEventPlugin;
import com.github.linyuzai.arkevent.core.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;

import java.util.Map;

public class ArkMqEventConditionFilter implements ArkEventConditionFilter {

    @Override
    public boolean filter(ArkEventSubscriber subscriber, Object event, Map args) {
        return (!ArkEventPlugin.isRemote(args)) &&
                (event instanceof ArkMqEvent ||
                        event.getClass().isAnnotationPresent(MqEvent.class));
    }
}
