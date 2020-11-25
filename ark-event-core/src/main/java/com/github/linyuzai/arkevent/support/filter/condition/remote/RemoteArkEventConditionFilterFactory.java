package com.github.linyuzai.arkevent.support.filter.condition.remote;

import com.github.linyuzai.arkevent.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.ArkEventSubscriber;

public class RemoteArkEventConditionFilterFactory implements ArkEventConditionFilter.Factory {

    private RemoteArkEventConditionFilter filter = new RemoteArkEventConditionFilter();

    @Override
    public ArkEventConditionFilter getConditionFilter(ArkEventSubscriber arkEventSubscriber) {
        if (arkEventSubscriber instanceof RemoteArkEventSubscriberMask) {
            return filter;
        }
        return null;
    }
}
