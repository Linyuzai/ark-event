package com.github.linyuzai.arkevent.support.filter.condition.remote;

import com.github.linyuzai.arkevent.ArkEvent;
import com.github.linyuzai.arkevent.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.ArkEventFromRemote;
import com.github.linyuzai.arkevent.ArkEventSubscriber;

import java.util.Arrays;

public class RemoteArkEventConditionFilter implements ArkEventConditionFilter {

    @Override
    public boolean filter(ArkEventSubscriber subscriber, ArkEvent arkEvent, Object... args) {
        return ArkEventFromRemote.isRemote(args);
    }
}
