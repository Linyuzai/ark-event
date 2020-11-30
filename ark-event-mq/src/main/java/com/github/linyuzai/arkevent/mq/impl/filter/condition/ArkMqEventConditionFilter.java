package com.github.linyuzai.arkevent.mq.impl.filter.condition;

import com.github.linyuzai.arkevent.basic.ArkEvent;
import com.github.linyuzai.arkevent.basic.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.basic.ArkEventSubscriber;

public class ArkMqEventConditionFilter implements ArkEventConditionFilter {

    @Override
    public boolean filter(ArkEventSubscriber subscriber, ArkEvent arkEvent, Object... args) {
        return arkEvent instanceof ArkMqEvent || arkEvent.getClass().isAnnotationPresent(MqEvent.class);
    }
}
