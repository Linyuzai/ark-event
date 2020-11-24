package com.github.linyuzai.arkevent.support.sorter.publish;

import com.github.linyuzai.arkevent.ArkEvent;
import com.github.linyuzai.arkevent.ArkEventPublishSorter;
import com.github.linyuzai.arkevent.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
import com.github.linyuzai.arkevent.support.strategy.publish.async.AsyncArkEventPublishStrategy;

public class AsyncArkEventPublishSorter implements ArkEventPublishSorter {

    @Override
    public int sortOrder(ArkEventPublishStrategy strategy, ArkEventSubscriber subscriber, ArkEvent event, Object... args) {
        if (strategy instanceof AsyncArkEventPublishStrategy) {
            return 1;
        }
        return 0;
    }
}
