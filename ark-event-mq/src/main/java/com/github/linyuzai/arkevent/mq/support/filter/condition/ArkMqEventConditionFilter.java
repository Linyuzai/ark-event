package com.github.linyuzai.arkevent.mq.support.filter.condition;

import com.github.linyuzai.arkevent.basic.ArkEvent;
import com.github.linyuzai.arkevent.basic.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.basic.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.support.filter.condition.ArkMqEvent;
import com.github.linyuzai.arkevent.mq.support.filter.condition.MqEvent;

public class ArkMqEventConditionFilter implements ArkEventConditionFilter {

    @Override
    public boolean filter(ArkEventSubscriber subscriber, ArkEvent arkEvent, Object... args) {
        return arkEvent instanceof ArkMqEvent || arkEvent.getClass().isAnnotationPresent(MqEvent.class);
    }
}
