package com.github.linyuzai.arkevent.impl;

import com.github.linyuzai.arkevent.*;
import com.github.linyuzai.arkevent.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.exception.ArkEventException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class DefaultArkEventDispatcher implements ArkEventDispatcher {

    private Map<Class<? extends ArkEventSubscriber>, Collection<ArkEventConditionFilter>> subscriberConditionFilterMap = new ConcurrentHashMap<>();

    private Map<Class<? extends ArkEventSubscriber>, ArkEventPublishStrategy> subscriberPublishStrategyMap = new ConcurrentHashMap<>();

    private Collection<ArkEventSubscriber> subscribers = new CopyOnWriteArrayList<>();

    private Collection<ArkEventConditionFilter.Factory> conditionFilterFactories = new CopyOnWriteArrayList<>();

    private Collection<ArkEventPublishStrategy.Adapter> publishStrategyAdapters = new CopyOnWriteArrayList<>();

    private ArkEventExceptionHandler exceptionHandler;

    public void registerSubscriber(Collection<? extends ArkEventSubscriber> subscribers) {
        subscribers.forEach(this::registerSubscriber);
    }

    public synchronized void registerSubscriber(ArkEventSubscriber subscriber) {
        if (subscriber == null) {
            throw new ArkEventException("ArkEventSubscriber is null");
        }
        subscribers.add(subscriber);
        filterConditions(subscriber);
        adaptPublishStrategy(subscriber);
    }

    private void filterConditions(ArkEventSubscriber subscriber) {
        Collection<ArkEventConditionFilter> filters = subscriberConditionFilterMap.get(subscriber.getClass());
        if (filters == null) {
            Collection<ArkEventConditionFilter> newFilters = new CopyOnWriteArrayList<>();
            for (ArkEventConditionFilter.Factory filterFactory : conditionFilterFactories) {
                ArkEventConditionFilter conditionFilter = filterFactory.getConditionFilter(subscriber);
                if (conditionFilter != null) {
                    newFilters.add(conditionFilter);
                }
            }
            subscriberConditionFilterMap.put(subscriber.getClass(), newFilters);
        }
    }

    public Collection<ArkEventSubscriber> getSubscribers() {
        return subscribers;
    }

    public void addConditionFilterFactory(Collection<? extends ArkEventConditionFilter.Factory> factories) {
        factories.forEach(this::addConditionFilterFactory);
    }

    public synchronized void addConditionFilterFactory(ArkEventConditionFilter.Factory factory) {
        if (factory == null) {
            throw new ArkEventException("ArkEventConditionFilter.Factory is null");
        }
        conditionFilterFactories.add(factory);
        refreshConditionFilters(factory);
    }

    private void refreshConditionFilters(ArkEventConditionFilter.Factory filterFactory) {
        for (ArkEventSubscriber subscriber : subscribers) {
            ArkEventConditionFilter conditionFilter = filterFactory.getConditionFilter(subscriber);
            if (conditionFilter != null) {
                Collection<ArkEventConditionFilter> filters = subscriberConditionFilterMap.get(subscriber.getClass());
                if (filters == null) {
                    filterConditions(subscriber);
                } else {
                    filters.add(conditionFilter);
                }
            }
        }
    }

    public Collection<ArkEventConditionFilter.Factory> getConditionFilterFactories() {
        return conditionFilterFactories;
    }

    public void addPublishStrategyAdapter(Collection<? extends ArkEventPublishStrategy.Adapter> adapters) {
        adapters.forEach(this::addPublishStrategyAdapter);
    }

    public synchronized void addPublishStrategyAdapter(ArkEventPublishStrategy.Adapter adapter) {
        if (adapter == null) {
            throw new ArkEventException("ArkEventPublishStrategy.Adapter is null");
        }
        this.publishStrategyAdapters.add(adapter);
        for (ArkEventSubscriber subscriber : subscribers) {
            adaptPublishStrategy(subscriber);
        }
    }

    private void adaptPublishStrategy(ArkEventSubscriber subscriber) {
        for (ArkEventPublishStrategy.Adapter strategyAdapter : publishStrategyAdapters) {
            ArkEventPublishStrategy strategy = strategyAdapter.adapt(subscriber);
            if (strategy != null) {
                subscriberPublishStrategyMap.put(subscriber.getClass(), strategy);
                break;
            }
        }
    }

    public Collection<ArkEventPublishStrategy.Adapter> getPublishStrategyAdapters() {
        return publishStrategyAdapters;
    }

    public ArkEventExceptionHandler getExceptionHandler() {
        return exceptionHandler;
    }

    public void setExceptionHandler(ArkEventExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public void dispatch(ArkEvent event) {
        ArkEvent.PublishConfiguration configuration = event.getPublishConfiguration() == null ?
                ArkEvent.PublishConfiguration.EMPTY : event.getPublishConfiguration();
        ArkEventExceptionHandler ce = configuration.getEventExceptionHandler() == null ?
                exceptionHandler : configuration.getEventExceptionHandler();
        if (ce == null) {
            throw new ArkEventException("ArkEventExceptionHandler is null");
        }
        List<ArkEventSubscriber> filterSubscribers = new ArrayList<>();
        for (ArkEventSubscriber subscriber : subscribers) {
            Collection<ArkEventConditionFilter> filters = subscriberConditionFilterMap.getOrDefault(subscriber.getClass(), Collections.emptySet());
            Collection<ArkEventConditionFilter> allFilters = new ArrayList<>(filters);
            Collection<ArkEventConditionFilter> cfs = configuration.getConditionFilters();
            if (cfs != null) {
                allFilters.addAll(cfs);
            }
            if (filterSubscriber(event, allFilters)) {
                filterSubscribers.add(subscriber);
            }
        }
        List<ArkEventPublisher> publishers = new ArrayList<>();
        ArkEventPublishStrategy cs = configuration.getPublishStrategy();
        if (cs == null) {
            for (ArkEventSubscriber filterSubscriber : filterSubscribers) {
                ArkEventPublishStrategy strategy = subscriberPublishStrategyMap.get(filterSubscriber.getClass());
                if (strategy != null) {
                    publishers.add(new DefaultArkEventPublisher(filterSubscriber, strategy, ce));
                }
            }
        } else {
            for (ArkEventSubscriber filterSubscriber : filterSubscribers) {
                publishers.add(new DefaultArkEventPublisher(filterSubscriber, cs, ce));
            }
        }
        for (ArkEventPublisher publisher : publishers) {
            publisher.publish(event);
        }
    }

    private boolean filterSubscriber(ArkEvent event, Collection<ArkEventConditionFilter> filters) {
        if (filters == null) {
            return false;
        }
        for (ArkEventConditionFilter filter : filters) {
            if (!filter.filter(event)) {
                return false;
            }
        }
        return true;
    }
}
