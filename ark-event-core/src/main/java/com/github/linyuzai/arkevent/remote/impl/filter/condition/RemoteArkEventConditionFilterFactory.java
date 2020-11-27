package com.github.linyuzai.arkevent.remote.impl.filter.condition;

import com.github.linyuzai.arkevent.basic.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.basic.ArkEventSubscriber;
import com.github.linyuzai.arkevent.remote.ArkEventRemoter;

public class RemoteArkEventConditionFilterFactory implements ArkEventConditionFilter.Factory {

    private RemoteArkEventConditionFilter filter = new RemoteArkEventConditionFilter();

    @Override
    public ArkEventConditionFilter getConditionFilter(ArkEventSubscriber arkEventSubscriber) {
        if (arkEventSubscriber instanceof ArkEventRemoter) {
            return filter;
        }
        return null;
    }
}
