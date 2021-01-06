package com.github.linyuzai.arkevent.core;

import java.util.Collection;
import java.util.Map;

public interface ArkEventPublishListener {

    default void onPublishStarted(Object event, Map<Object, Object> args) {

    }

    default void onEventArgsProcessed(Collection<? extends ArkEventArgsProcessor> argsProcessors,
                                      Object event, Map<Object, Object> args) {

    }

    default void onEachSubscriberConditionsFiltered(boolean filter, ArkEventSubscriber subscriber,
                                                    Collection<? extends ArkEventConditionFilter> filters,
                                                    Object event, Map<Object, Object> args) {
    }

    default void onSubscribersFiltered(Collection<? extends ArkEventSubscriber> subscribers,
                                       Object event, Map<Object, Object> args) {
    }

    default void onEachSubscriberPublishStrategyAdapted(ArkEventPublishStrategy strategy,
                                                        ArkEventSubscriber subscriber,
                                                        Object event, Map<Object, Object> args) {
    }

    default void onEachSubscriberExceptionHandlerAdapted(ArkEventExceptionHandler handler,
                                                         ArkEventSubscriber subscriber,
                                                         Object event, Map<Object, Object> args) {
    }

    default void onPublishCompleted(Object event, Map<Object, Object> args) {

    }

    default void onPublishError(Throwable e, Object event, Map<Object, Object> args) {

    }
}
