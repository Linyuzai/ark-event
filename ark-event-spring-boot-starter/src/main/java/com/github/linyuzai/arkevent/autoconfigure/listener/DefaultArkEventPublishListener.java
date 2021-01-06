package com.github.linyuzai.arkevent.autoconfigure.listener;

import com.github.linyuzai.arkevent.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultArkEventPublishListener implements ArkEventPublishListener {

    private static final Logger log = LoggerFactory.getLogger(DefaultArkEventPublishListener.class);

    private static final String TAG = "【ArkEvent】";

    public String getObjectName(Object object) {
        return object.getClass().getSimpleName();
    }

    public String getObjectsName(Collection<?> objects) {
        return objects.stream().map(it -> it.getClass().getSimpleName()).collect(Collectors.toList()).toString();
    }

    public String getArgsProcessorsName(Collection<? extends ArkEventArgsProcessor> argsProcessors) {
        return getObjectsName(argsProcessors);
    }

    public String getSubscriberName(ArkEventSubscriber subscriber) {
        return getObjectName(subscriber);
    }

    public String getSubscribersName(Collection<? extends ArkEventSubscriber> subscribers) {
        return getObjectsName(subscribers);
    }

    public String getFiltersName(Collection<? extends ArkEventConditionFilter> filters) {
        return getObjectsName(filters);
    }

    public String getStrategyName(ArkEventPublishStrategy strategy) {
        return getObjectName(strategy);
    }

    public String getHandlerName(ArkEventExceptionHandler handler) {
        return getObjectName(handler);
    }

    public String getEventName(Object event) {
        return getObjectName(event);
    }

    @Override
    public void onPublishStarted(Object event, Map<Object, Object> args) {
        log.info("{} {} start publish with args {}",
                TAG, getEventName(event), args);
    }

    @Override
    public void onEventArgsProcessed(Collection<? extends ArkEventArgsProcessor> argsProcessors,
                                     Object event, Map<Object, Object> args) {
        log.info("{} Args processed {} on event {} with args {}",
                TAG, getArgsProcessorsName(argsProcessors), getEventName(event), args);
    }

    @Override
    public void onEachSubscriberConditionsFiltered(boolean filter, ArkEventSubscriber subscriber,
                                                   Collection<? extends ArkEventConditionFilter> filters,
                                                   Object event, Map<Object, Object> args) {
        log.info("{} Conditions {} on subscriber {} {} event {} with args {}",
                TAG, getFiltersName(filters), getSubscriberName(subscriber),
                filter ? "matched" : "not matched", getEventName(event), args);
    }

    @Override
    public void onSubscribersFiltered(Collection<? extends ArkEventSubscriber> subscribers,
                                      Object event, Map<Object, Object> args) {
        log.info("{} {} subscribers {} matched event {} with args {}",
                TAG, subscribers.size(), getSubscribersName(subscribers), getEventName(event), args);
    }

    @Override
    public void onEachSubscriberPublishStrategyAdapted(ArkEventPublishStrategy strategy,
                                                       ArkEventSubscriber subscriber,
                                                       Object event, Map<Object, Object> args) {
        log.info("{} Publish strategy {} adapted subscriber {} on event {} with args {}",
                TAG, getStrategyName(strategy), getSubscriberName(subscriber), getEventName(event), args);
    }

    @Override
    public void onEachSubscriberExceptionHandlerAdapted(ArkEventExceptionHandler handler,
                                                        ArkEventSubscriber subscriber,
                                                        Object event, Map<Object, Object> args) {
        log.info("{} Exception handler {} adapted subscriber {} on event {} with args {}",
                TAG, getHandlerName(handler), getSubscriberName(subscriber), getEventName(event), args);
    }

    @Override
    public void onPublishCompleted(Object event, Map<Object, Object> args) {
        log.info("{} Event {} complete publish with args {}",
                TAG, getEventName(event), args);
    }

    @Override
    public void onPublishError(Throwable e, Object event, Map<Object, Object> args) {
        log.error("{} Event {} publish error {} with args {}",
                TAG, getEventName(event), e.getMessage(), args);
    }
}
