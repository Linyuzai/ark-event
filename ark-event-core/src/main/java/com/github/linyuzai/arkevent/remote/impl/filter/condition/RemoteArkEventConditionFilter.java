package com.github.linyuzai.arkevent.remote.impl.filter.condition;

import com.github.linyuzai.arkevent.basic.ArkEvent;
import com.github.linyuzai.arkevent.basic.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.remote.ArkEventFromRemote;
import com.github.linyuzai.arkevent.basic.ArkEventSubscriber;

public class RemoteArkEventConditionFilter implements ArkEventConditionFilter {

    @Override
    public boolean filter(ArkEventSubscriber subscriber, ArkEvent arkEvent, Object... args) {
        return ArkEventFromRemote.isRemote(args);
    }
}
