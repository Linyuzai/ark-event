package com.github.linyuzai.arkevent.mq.impl.filter.condition;

import com.github.linyuzai.arkevent.core.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.ArkMqEventSubscriber;

public class ArkMqEventConditionFilterFactory implements ArkEventConditionFilter.Factory {

    private ArkMqEventConditionFilter filter = new ArkMqEventConditionFilter();

    @Override
    public ArkEventConditionFilter getConditionFilter(ArkEventSubscriber arkEventSubscriber) {
        if (arkEventSubscriber instanceof ArkMqEventSubscriber) {
            return filter;
        }
        return null;
    }
}
