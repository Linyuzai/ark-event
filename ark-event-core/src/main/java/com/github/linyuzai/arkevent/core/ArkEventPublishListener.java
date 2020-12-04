package com.github.linyuzai.arkevent.core;

import java.util.Collection;
import java.util.Map;

public interface ArkEventPublishListener {

    default void onPublishStarted(ArkEvent event, Map<Object, Object> args) {

    }

    default void onEachSubscriberConditionsFiltered(boolean filter, ArkEventSubscriber subscriber,
                                                    Collection<? extends ArkEventConditionFilter> filters,
                                                    ArkEvent event, Map<Object, Object> args) {
    }

    default void onSubscribersFiltered(Collection<? extends ArkEventSubscriber> subscribers,
                                       ArkEvent event, Map<Object, Object> args) {
    }

    default void onEachSubscriberPublishStrategyAdapted(ArkEventPublishStrategy strategy,
                                                        ArkEventSubscriber subscriber,
                                                        ArkEvent event, Map<Object, Object> args) {
    }

    default void onEachSubscriberExceptionHandlerAdapted(ArkEventExceptionHandler handler,
                                                         ArkEventSubscriber subscriber,
                                                         ArkEvent event, Map<Object, Object> args) {
    }

    default void onSubscribersSorted(Collection<? extends ArkEventSubscriber> subscribers,
                                     ArkEvent event, Map<Object, Object> args) {
    }

    default void onPublishCompleted(ArkEvent event, Map<Object, Object> args) {

    }

    default void onPublishError(Throwable e, ArkEvent event, Map<Object, Object> args) {

    }
}
