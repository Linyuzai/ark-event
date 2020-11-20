package com.github.linyuzai.arkevent;

public interface ArkEventConditionFilter {

    boolean filter(ArkEvent event);

    interface Factory {

        ArkEventConditionFilter getConditionFilter(ArkEventSubscriber subscriber);
    }
}
