package com.github.linyuzai.arkevent.mq.impl.sorter.publish;

import com.github.linyuzai.arkevent.core.ArkEventPublishSorter;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.ArkMqEventSubscriber;

public class ArkMqEventPublishSorter implements ArkEventPublishSorter {

    @Override
    public boolean highOrder(ArkEventSubscriber subscriber) {
        return subscriber instanceof ArkMqEventSubscriber;
    }

    @Override
    public int order() {
        return PUBLISH_SORTER_MQ;
    }
}
