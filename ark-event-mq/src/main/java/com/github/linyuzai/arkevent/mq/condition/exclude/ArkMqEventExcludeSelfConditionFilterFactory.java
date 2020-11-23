package com.github.linyuzai.arkevent.mq.condition.exclude;

import com.github.linyuzai.arkevent.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.ArkMqEventSubscriber;
import com.github.linyuzai.arkevent.mq.condition.type.ArkMqEventConditionFilter;

public class ArkMqEventExcludeSelfConditionFilterFactory implements ArkEventConditionFilter.Factory {

    private ArkMqEventExcludeSelfConditionFilter filter = new ArkMqEventExcludeSelfConditionFilter();

    @Override
    public ArkEventConditionFilter getConditionFilter(ArkEventSubscriber arkEventSubscriber) {
        if (arkEventSubscriber instanceof ArkMqEventSubscriber) {
            return filter;
        }
        return null;
    }
}
