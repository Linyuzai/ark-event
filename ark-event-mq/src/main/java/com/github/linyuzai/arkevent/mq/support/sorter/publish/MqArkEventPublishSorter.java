package com.github.linyuzai.arkevent.mq.support.sorter.publish;

import com.github.linyuzai.arkevent.ArkEvent;
import com.github.linyuzai.arkevent.ArkEventPublishSorter;
import com.github.linyuzai.arkevent.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.support.strategy.publish.MqArkEventPublishStrategy;

public class MqArkEventPublishSorter implements ArkEventPublishSorter {

    @Override
    public int sortOrder(ArkEventPublishStrategy strategy, ArkEventSubscriber subscriber, ArkEvent event, Object... args) {
        if (strategy instanceof MqArkEventPublishStrategy) {
            return 2;
        }
        return 0;
    }
}
