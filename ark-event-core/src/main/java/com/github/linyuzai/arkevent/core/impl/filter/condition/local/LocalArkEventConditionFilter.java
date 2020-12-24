package com.github.linyuzai.arkevent.core.impl.filter.condition.local;

import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.core.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.support.ArkEventPlugin;

import java.util.Map;

public class LocalArkEventConditionFilter implements ArkEventConditionFilter {

    public static final LocalArkEventConditionFilter INSTANCE = new LocalArkEventConditionFilter();

    @Override
    public boolean filter(ArkEventSubscriber subscriber, ArkEvent event, Map<Object, Object> args) {
        return !ArkEventPlugin.isRemote(args);
    }
}
