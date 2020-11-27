package com.github.linyuzai.arkevent.remote.impl.sorter.publish;

import com.github.linyuzai.arkevent.basic.ArkEvent;
import com.github.linyuzai.arkevent.basic.ArkEventPublishSorter;
import com.github.linyuzai.arkevent.basic.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.basic.ArkEventSubscriber;
import com.github.linyuzai.arkevent.remote.ArkEventRemoter;

public class RemoteArkEventPublishSorter implements ArkEventPublishSorter {

    @Override
    public boolean highOrder(ArkEventPublishStrategy strategy, ArkEventSubscriber subscriber, ArkEvent event, Object... args) {
        return subscriber instanceof ArkEventRemoter;
    }

    @Override
    public int order() {
        return PUBLISH_SORTER_REMOTE;
    }
}