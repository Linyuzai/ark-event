package com.github.linyuzai.arkevent.autoconfigure.listener;

import com.github.linyuzai.arkevent.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class DefaultArkEventPublishListener implements ArkEventPublishListener {

    private static final Logger log = LoggerFactory.getLogger(DefaultArkEventPublishListener.class);

    private static final String TAG = "【ArkEvent】";

    @Override
    public void onPublishStarted(ArkEvent event, Object... args) {
        log.info("{} {} start publish with args {}", TAG, event, Arrays.toString(args));
    }

    @Override
    public void onEachSubscriberConditionsFiltered(boolean filter, ArkEventSubscriber subscriber, List<? extends ArkEventConditionFilter> filters, ArkEvent event, Object... args) {
        log.info("{} Subscriber {} which has conditions {} is {} for {} with args {}", TAG, subscriber, filters.toString(), filter ? "matched" : "not matched", event, Arrays.toString(args));
    }

    @Override
    public void onSubscribersFiltered(List<? extends ArkEventSubscriber> subscribers, ArkEvent event, Object... args) {
        log.info("{} Subscribers {} is matched for {} with args {}", TAG, subscribers.toString(), event, Arrays.toString(args));
    }

    @Override
    public void onEachSubscriberPublishStrategyAdapted(ArkEventPublishStrategy strategy, ArkEventSubscriber subscriber, ArkEvent event, Object... args) {
        log.info("{} Subscriber {} is adapted publish strategy {} for {} with args {}", TAG, subscriber, strategy, event, Arrays.toString(args));
    }

    @Override
    public void onEachSubscriberExceptionHandlerAdapted(ArkEventExceptionHandler handler, ArkEventSubscriber subscriber, ArkEvent event, Object... args) {
        log.info("{} Subscriber {} is adapted exception handler {} for {} with args {}", TAG, subscriber, handler, event, Arrays.toString(args));
    }

    @Override
    public void onPublishersCreated(List<? extends ArkEventPublisher> publishers, ArkEvent event, Object... args) {
        log.info("{} {} publishers created for {} with args {}", TAG, publishers.size(), event, Arrays.toString(args));
    }

    @Override
    public void onPublishersSorted(List<? extends ArkEventPublisher> publishers, ArkEvent event, Object... args) {
        log.info("{} Publishers sorted for {} with args {}", TAG, event, Arrays.toString(args));
    }

    @Override
    public void onPublishFinished(ArkEvent event, Object... args) {
        log.info("{} {} finish publish with args {}", TAG, event, Arrays.toString(args));
    }
}
