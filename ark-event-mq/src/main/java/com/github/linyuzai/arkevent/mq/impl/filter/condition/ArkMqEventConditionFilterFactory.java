package com.github.linyuzai.arkevent.mq.impl.filter.condition;

import com.github.linyuzai.arkevent.basic.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.basic.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.ArkMqEventRemoter;

public class ArkMqEventConditionFilterFactory implements ArkEventConditionFilter.Factory {

    private ArkMqEventConditionFilter filter = new ArkMqEventConditionFilter();

    @Override
    public ArkEventConditionFilter getConditionFilter(ArkEventSubscriber arkEventSubscriber) {
        if (arkEventSubscriber instanceof ArkMqEventRemoter) {
            return filter;
        }
        return null;
    }
}
