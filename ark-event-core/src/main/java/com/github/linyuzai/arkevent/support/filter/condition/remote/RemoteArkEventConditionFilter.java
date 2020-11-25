package com.github.linyuzai.arkevent.support.filter.condition.remote;

import com.github.linyuzai.arkevent.ArkEvent;
import com.github.linyuzai.arkevent.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.ArkEventFromRemote;
import com.github.linyuzai.arkevent.ArkEventSubscriber;

import java.util.Arrays;

public class RemoteArkEventConditionFilter implements ArkEventConditionFilter {

    @Override
    public boolean filter(ArkEventSubscriber subscriber, ArkEvent arkEvent, Object... args) {
        long count = Arrays.stream(args).filter(it -> it instanceof ArkEventFromRemote).count();
        return count == 0;
    }
}
