package com.github.linyuzai.arkevent.support.sorter.publish.remote;

import com.github.linyuzai.arkevent.ArkEvent;
import com.github.linyuzai.arkevent.ArkEventPublishSorter;
import com.github.linyuzai.arkevent.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
import com.github.linyuzai.arkevent.support.filter.condition.remote.RemoteArkEventSubscriberMask;

public class RemoteArkEventPublishSorter implements ArkEventPublishSorter {

    @Override
    public boolean highOrder(ArkEventPublishStrategy strategy, ArkEventSubscriber subscriber, ArkEvent event, Object... args) {
        return subscriber instanceof RemoteArkEventSubscriberMask;
    }

    @Override
    public int order() {
        return PUBLISH_SORTER_REMOTE;
    }
}
