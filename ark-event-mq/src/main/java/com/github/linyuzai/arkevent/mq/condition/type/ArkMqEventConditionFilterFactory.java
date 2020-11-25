package com.github.linyuzai.arkevent.mq.condition.type;

import com.github.linyuzai.arkevent.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.ArkEventSubscriber;

public class ArkMqEventConditionFilterFactory implements ArkEventConditionFilter.Factory {

    private ArkMqEventConditionFilter filter = new ArkMqEventConditionFilter();

    @Override
    public ArkEventConditionFilter getConditionFilter(ArkEventSubscriber arkEventSubscriber) {
        if (arkEventSubscriber instanceof ArkMqMask) {
            return filter;
        }
        return null;
    }
}
