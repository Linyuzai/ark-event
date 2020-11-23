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

    private Map<Class<? extends ArkEventSubscriber>, ArkEventExceptionHandler> subscriberExceptionHandlerMap = new ConcurrentHashMap<>();

    private Collection<ArkEventSubscriber> subscribers = new CopyOnWriteArrayList<>();

    private Collection<ArkEventConditionFilter.Factory> conditionFilterFactories = new CopyOnWriteArrayList<>();

    private Collection<ArkEventPublishStrategy.Adapter> publishStrategyAdapters = new CopyOnWriteArrayList<>();

    private Collection<ArkEventExceptionHandler.Adapter> exceptionHandlerAdapters = new CopyOnWriteArrayList<>();

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
        adaptExceptionHandler(subscriber);
    }

    public Collection<ArkEventSubscriber> getSubscribers() {
        return subscribers;
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

    public Collection<ArkEventConditionFilter.Factory> getConditionFilterFactories() {
        return conditionFilterFactories;
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

    public Collection<ArkEventPublishStrategy.Adapter> getPublishStrategyAdapters() {
        return publishStrategyAdapters;
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

    public void addExceptionHandlerAdapter(Collection<? extends ArkEventExceptionHandler.Adapter> adapters) {
        adapters.forEach(this::addExceptionHandlerAdapter);
    }

    public synchronized void addExceptionHandlerAdapter(ArkEventExceptionHandler.Adapter adapter) {
        if (adapter == null) {
            throw new ArkEventException("ArkEventExceptionHandler.Adapter is null");
        }
        this.exceptionHandlerAdapters.add(adapter);
        for (ArkEventSubscriber subscriber : subscribers) {
            adaptExceptionHandler(subscriber);
        }
    }

    public Collection<ArkEventExceptionHandler.Adapter> getExceptionHandlerAdapters() {
        return exceptionHandlerAdapters;
    }

    private void adaptExceptionHandler(ArkEventSubscriber subscriber) {
        for (ArkEventExceptionHandler.Adapter handlerAdapter : exceptionHandlerAdapters) {
            ArkEventExceptionHandler handler = handlerAdapter.adapt(subscriber);
            if (handler != null) {
                subscriberExceptionHandlerMap.put(subscriber.getClass(), handler);
                break;
            }
        }
    }

    @Override
    public void dispatch(ArkEvent event, Object... args) {
        List<ArkEventSubscriber> filterSubscribers = new ArrayList<>();
        for (ArkEventSubscriber subscriber : subscribers) {
            Collection<ArkEventConditionFilter> filters = subscriberConditionFilterMap.getOrDefault(subscriber.getClass(), Collections.emptySet());
            if (filterSubscriber(filters, event, args)) {
                filterSubscribers.add(subscriber);
            }
        }
        List<ArkEventPublisher> publishers = new ArrayList<>();
        for (ArkEventSubscriber filterSubscriber : filterSubscribers) {
            ArkEventPublishStrategy strategy = subscriberPublishStrategyMap.get(filterSubscriber.getClass());
            if (strategy == null) {
                throw new ArkEventException("No execute strategy found");
            }
            ArkEventExceptionHandler handler = subscriberExceptionHandlerMap.get(filterSubscriber.getClass());
            if (handler == null) {
                throw new ArkEventException("No exception handler found");
            }
            publishers.add(new DefaultArkEventPublisher(filterSubscriber, strategy, handler));
        }
        for (ArkEventPublisher publisher : publishers) {
            publisher.publish(event, args);
        }
    }

    private boolean filterSubscriber(Collection<ArkEventConditionFilter> filters, ArkEvent event, Object... args) {
        if (filters == null) {
            return false;
        }
        for (ArkEventConditionFilter filter : filters) {
            if (!filter.filter(event, args)) {
                return false;
            }
        }
        return true;
    }
}
