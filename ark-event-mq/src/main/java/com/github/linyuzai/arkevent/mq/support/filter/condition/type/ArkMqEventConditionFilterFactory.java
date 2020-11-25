package com.github.linyuzai.arkevent.mq.support.filter.condition.type;

import com.github.linyuzai.arkevent.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.ArkEventSubscriber;

public class ArkMqEventConditionFilterFactory implements ArkEventConditionFilter.Factory {

    private ArkMqEventConditionFilter filter = new ArkMqEventConditionFilter();

    @Override
    public ArkEventConditionFilter getConditionFilter(ArkEventSubscriber arkEventSubscriber) {
        if (arkEventSubscriber instanceof MqArkEventSubscriberMask) {
            return filter;
        }
        return null;
    }
}
