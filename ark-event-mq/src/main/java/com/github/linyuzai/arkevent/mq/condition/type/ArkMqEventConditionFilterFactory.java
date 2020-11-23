package com.github.linyuzai.arkevent.mq.condition.type;

import com.github.linyuzai.arkevent.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
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
