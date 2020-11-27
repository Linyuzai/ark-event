package com.github.linyuzai.arkevent.support.sorter.publish.async;

import com.github.linyuzai.arkevent.ArkEvent;
import com.github.linyuzai.arkevent.ArkEventPublishSorter;
import com.github.linyuzai.arkevent.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
import com.github.linyuzai.arkevent.support.strategy.publish.async.AsyncArkEventPublishStrategy;
import com.github.linyuzai.arkevent.support.strategy.publish.async.AsyncEventPublish;
import com.github.linyuzai.arkevent.support.strategy.publish.async.AsyncEventPublishStrategy;

public class AsyncArkEventPublishSorter implements ArkEventPublishSorter {

    @Override
    public boolean highOrder(ArkEventPublishStrategy strategy, ArkEventSubscriber subscriber, ArkEvent event, Object... args) {
        return !(subscriber instanceof AsyncEventPublishStrategy ||
                subscriber.getClass().isAnnotationPresent(AsyncEventPublish.class));
    }

    @Override
    public int order() {
        return PUBLISH_SORTER_ASYNC;
    }
}
