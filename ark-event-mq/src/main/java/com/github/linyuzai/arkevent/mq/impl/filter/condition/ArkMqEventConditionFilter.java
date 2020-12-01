package com.github.linyuzai.arkevent.mq.impl.filter.condition;

import com.github.linyuzai.arkevent.support.ArkEventPlugin;
import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.core.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;

public class ArkMqEventConditionFilter implements ArkEventConditionFilter {

    @Override
    public boolean filter(ArkEventSubscriber subscriber, ArkEvent arkEvent, Object... args) {
        return (!ArkEventPlugin.isFromRemote(args)) &&
                (arkEvent instanceof ArkMqEvent ||
                        arkEvent.getClass().isAnnotationPresent(MqEvent.class));
    }
}
