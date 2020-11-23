package com.github.linyuzai.arkevent;

public interface ArkEventConditionFilter {

    boolean filter(ArkEvent event, Object... args);

    interface Factory {

        ArkEventConditionFilter getConditionFilter(ArkEventSubscriber subscriber);
    }
}
