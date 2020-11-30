package com.github.linyuzai.arkevent.basic;

import java.util.List;

public interface ArkEventPublishListener {

    default void onPublishStarted(ArkEvent event, Object... args) {

    }

    default void onEachSubscriberConditionsFiltered(boolean filter, ArkEventSubscriber subscriber,
                                                    List<? extends ArkEventConditionFilter> filters,
                                                    ArkEvent event, Object... args) {
    }

    default void onSubscribersFiltered(List<? extends ArkEventSubscriber> subscribers,
                                       ArkEvent event, Object... args) {
    }

    default void onEachSubscriberPublishStrategyAdapted(ArkEventPublishStrategy strategy,
                                                        ArkEventSubscriber subscriber,
                                                        ArkEvent event, Object... args) {
    }

    default void onEachSubscriberExceptionHandlerAdapted(ArkEventExceptionHandler handler,
                                                         ArkEventSubscriber subscriber,
                                                         ArkEvent event, Object... args) {
    }

    default void onPublishersCreated(List<? extends ArkEventPublisher> publishers,
                                     ArkEvent event, Object... args) {
    }

    default void onPublishersSorted(List<? extends ArkEventPublisher> publishers,
                                    ArkEvent event, Object... args) {
    }

    default void onPublishFinished(ArkEvent event, Object... args) {

    }
}
