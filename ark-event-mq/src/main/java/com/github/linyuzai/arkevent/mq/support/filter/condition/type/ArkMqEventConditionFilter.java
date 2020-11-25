package com.github.linyuzai.arkevent.mq.support.filter.condition.type;

import com.github.linyuzai.arkevent.ArkEvent;
import com.github.linyuzai.arkevent.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.support.filter.condition.annotation.ArkMqEvent;

public class ArkMqEventConditionFilter implements ArkEventConditionFilter {

    @Override
    public boolean filter(ArkEventSubscriber subscriber, ArkEvent arkEvent, Object... args) {
        return arkEvent instanceof com.github.linyuzai.arkevent.mq.support.filter.condition.ArkMqEvent || arkEvent.getClass().isAnnotationPresent(ArkMqEvent.class);
    }
}
