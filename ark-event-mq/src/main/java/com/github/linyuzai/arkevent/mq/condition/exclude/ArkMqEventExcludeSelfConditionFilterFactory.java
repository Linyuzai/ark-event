package com.github.linyuzai.arkevent.mq.condition.exclude;

import com.github.linyuzai.arkevent.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.ArkMqEventSubscriberMask;

public class ArkMqEventExcludeSelfConditionFilterFactory implements ArkEventConditionFilter.Factory {

    private ArkMqExcludeSelfEventConditionFilter filter = new ArkMqExcludeSelfEventConditionFilter();

    @Override
    public ArkEventConditionFilter getConditionFilter(ArkEventSubscriber arkEventSubscriber) {
        if (arkEventSubscriber instanceof ArkMqEventSubscriberMask) {
            return filter;
        }
        return null;
    }
}
