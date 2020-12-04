package com.github.linyuzai.arkevent.autoconfigure.listener;

import com.github.linyuzai.arkevent.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class DefaultArkEventPublishListener implements ArkEventPublishListener {

    private static final Logger log = LoggerFactory.getLogger(DefaultArkEventPublishListener.class);

    private static final String TAG = "【ArkEvent】";

    @Override
    public void onPublishStarted(ArkEvent event, Map<Object, Object> args) {
        log.info("{} {} start publish with args {}",
                TAG, event, args);
    }

    @Override
    public void onEachSubscriberConditionsFiltered(boolean filter, ArkEventSubscriber subscriber,
                                                   Collection<? extends ArkEventConditionFilter> filters,
                                                   ArkEvent event, Map<Object, Object> args) {
        log.info("{} Subscriber {} which has conditions {} is {} for {} with args {}",
                TAG, subscriber, filters.toString(), filter ? "matched" : "not matched", event, args);
    }

    @Override
    public void onSubscribersFiltered(Collection<? extends ArkEventSubscriber> subscribers,
                                      ArkEvent event, Map<Object, Object> args) {
        log.info("{} Subscribers {} is matched for {} with args {}",
                TAG, subscribers.toString(), event, args);
    }

    @Override
    public void onEachSubscriberPublishStrategyAdapted(ArkEventPublishStrategy strategy,
                                                       ArkEventSubscriber subscriber,
                                                       ArkEvent event, Map<Object, Object> args) {
        log.info("{} Subscriber {} is adapted publish strategy {} for {} with args {}",
                TAG, subscriber, strategy, event, args);
    }

    @Override
    public void onEachSubscriberExceptionHandlerAdapted(ArkEventExceptionHandler handler,
                                                        ArkEventSubscriber subscriber,
                                                        ArkEvent event, Map<Object, Object> args) {
        log.info("{} Subscriber {} is adapted exception handler {} for {} with args {}",
                TAG, subscriber, handler, event, args);
    }

    @Override
    public void onSubscribersSorted(Collection<? extends ArkEventSubscriber> subscribers,
                                    ArkEvent event, Map<Object, Object> args) {
        log.info("{} Subscribers {} sorted for {} with args {}",
                TAG, subscribers, event, args);
    }

    @Override
    public void onPublishCompleted(ArkEvent event, Map<Object, Object> args) {
        log.info("{} {} complete publish with args {}",
                TAG, event, args);
    }

    @Override
    public void onPublishError(Throwable e, ArkEvent event, Map<Object, Object> args) {
        log.error("{} {} publish error {} with args {}",
                TAG, event, e.getMessage(), args);
    }
}
