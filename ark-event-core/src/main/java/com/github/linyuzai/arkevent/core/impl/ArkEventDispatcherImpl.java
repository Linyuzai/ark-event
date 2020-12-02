package com.github.linyuzai.arkevent.core.impl;

import com.github.linyuzai.arkevent.core.*;
import com.github.linyuzai.arkevent.core.ArkEventException;
import com.github.linyuzai.arkevent.support.Order;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArkEventDispatcherImpl implements ArkEventDispatcher {

    private Map<ArkEventSubscriber, List<ArkEventConditionFilter>> subscriberConditionFilterMap = new ConcurrentHashMap<>();

    private Map<ArkEventSubscriber, ArkEventPublishStrategy> subscriberPublishStrategyMap = new ConcurrentHashMap<>();

    private Map<ArkEventSubscriber, ArkEventExceptionHandler> subscriberExceptionHandlerMap = new ConcurrentHashMap<>();

    private List<ArkEventSubscriber> subscribers = new CopyOnWriteArrayList<>();

    private List<ArkEventConditionFilter.Factory> conditionFilterFactories = new CopyOnWriteArrayList<>();

    private List<ArkEventPublishStrategy.Adapter> publishStrategyAdapters = new CopyOnWriteArrayList<>();

    private List<ArkEventExceptionHandler.Adapter> exceptionHandlerAdapters = new CopyOnWriteArrayList<>();

    private List<ArkEventPublishSorter> publishSorters = new CopyOnWriteArrayList<>();

    private List<ArkEventPublishListener> publishListeners = new CopyOnWriteArrayList<>();

    public synchronized void registerSubscriber(Collection<? extends ArkEventSubscriber> subscribers) {
        for (ArkEventSubscriber subscriber : subscribers) {
            if (subscriber == null) {
                throw new ArkEventException("ArkEventSubscriber is null");
            }
        }
        this.subscribers.addAll(subscribers);
        this.subscribers.sort(Comparator.comparingInt(Order::order));
        for (ArkEventSubscriber subscriber : subscribers) {
            filterConditions(subscriber);
            adaptPublishStrategy(subscriber);
            adaptExceptionHandler(subscriber);
        }
    }

    public synchronized void registerSubscriber(ArkEventSubscriber subscriber) {
        if (subscriber == null) {
            throw new ArkEventException("ArkEventSubscriber is null");
        }
        this.subscribers.add(subscriber);
        this.subscribers.sort(Comparator.comparingInt(Order::order));
        filterConditions(subscriber);
        adaptPublishStrategy(subscriber);
        adaptExceptionHandler(subscriber);
    }

    public List<ArkEventSubscriber> getSubscribers() {
        return subscribers;
    }

    public synchronized void addConditionFilterFactory(Collection<? extends ArkEventConditionFilter.Factory> factories) {
        for (ArkEventConditionFilter.Factory factory : factories) {
            if (factory == null) {
                throw new ArkEventException("ArkEventConditionFilter.Factory is null");
            }
        }
        for (ArkEventConditionFilter.Factory factory : factories) {
            conditionFilterFactories.add(factory);
            appendConditionFilter(factory);
        }
    }

    public synchronized void addConditionFilterFactory(ArkEventConditionFilter.Factory factory) {
        if (factory == null) {
            throw new ArkEventException("ArkEventConditionFilter.Factory is null");
        }
        conditionFilterFactories.add(factory);
        appendConditionFilter(factory);
    }

    public List<ArkEventConditionFilter.Factory> getConditionFilterFactories() {
        return conditionFilterFactories;
    }

    private void filterConditions(ArkEventSubscriber subscriber) {
        List<ArkEventConditionFilter> filters = subscriberConditionFilterMap.get(subscriber);
        if (filters == null) {
            List<ArkEventConditionFilter> newFilters = new CopyOnWriteArrayList<>();
            for (ArkEventConditionFilter.Factory filterFactory : conditionFilterFactories) {
                ArkEventConditionFilter conditionFilter = filterFactory.getConditionFilter(subscriber);
                if (conditionFilter != null) {
                    newFilters.add(conditionFilter);
                }
            }
            newFilters.sort(Comparator.comparingInt(Order::order));
            subscriberConditionFilterMap.put(subscriber, newFilters);
        }
    }

    private void appendConditionFilter(ArkEventConditionFilter.Factory filterFactory) {
        for (ArkEventSubscriber subscriber : subscribers) {
            ArkEventConditionFilter conditionFilter = filterFactory.getConditionFilter(subscriber);
            if (conditionFilter != null) {
                List<ArkEventConditionFilter> filters = subscriberConditionFilterMap.get(subscriber);
                if (filters == null) {
                    filterConditions(subscriber);
                } else {
                    filters.add(conditionFilter);
                    filters.sort(Comparator.comparingInt(Order::order));
                }
            }
        }
    }

    public synchronized void addPublishStrategyAdapter(Collection<? extends ArkEventPublishStrategy.Adapter> adapters) {
        for (ArkEventPublishStrategy.Adapter adapter : adapters) {
            if (adapter == null) {
                throw new ArkEventException("ArkEventPublishStrategy.Adapter is null");
            }
        }
        this.publishStrategyAdapters.addAll(adapters);
        this.publishStrategyAdapters.sort(Comparator.comparingInt(Order::order));
        for (ArkEventSubscriber subscriber : subscribers) {
            adaptPublishStrategy(subscriber);
        }
    }

    public synchronized void addPublishStrategyAdapter(ArkEventPublishStrategy.Adapter adapter) {
        if (adapter == null) {
            throw new ArkEventException("ArkEventPublishStrategy.Adapter is null");
        }
        this.publishStrategyAdapters.add(adapter);
        this.publishStrategyAdapters.sort(Comparator.comparingInt(Order::order));
        for (ArkEventSubscriber subscriber : subscribers) {
            adaptPublishStrategy(subscriber);
        }
    }

    public List<ArkEventPublishStrategy.Adapter> getPublishStrategyAdapters() {
        return publishStrategyAdapters;
    }

    private void adaptPublishStrategy(ArkEventSubscriber subscriber) {
        for (ArkEventPublishStrategy.Adapter strategyAdapter : publishStrategyAdapters) {
            ArkEventPublishStrategy strategy = strategyAdapter.adapt(subscriber);
            if (strategy != null) {
                subscriberPublishStrategyMap.put(subscriber, strategy);
                break;
            }
        }
    }

    public void addExceptionHandlerAdapter(Collection<? extends ArkEventExceptionHandler.Adapter> adapters) {
        for (ArkEventExceptionHandler.Adapter adapter : adapters) {
            if (adapter == null) {
                throw new ArkEventException("ArkEventExceptionHandler.Adapter is null");
            }
        }
        this.exceptionHandlerAdapters.addAll(adapters);
        this.exceptionHandlerAdapters.sort(Comparator.comparingInt(Order::order));
        for (ArkEventSubscriber subscriber : subscribers) {
            adaptExceptionHandler(subscriber);
        }
    }

    public synchronized void addExceptionHandlerAdapter(ArkEventExceptionHandler.Adapter adapter) {
        if (adapter == null) {
            throw new ArkEventException("ArkEventExceptionHandler.Adapter is null");
        }
        this.exceptionHandlerAdapters.add(adapter);
        this.exceptionHandlerAdapters.sort(Comparator.comparingInt(Order::order));
        for (ArkEventSubscriber subscriber : subscribers) {
            adaptExceptionHandler(subscriber);
        }
    }

    public List<ArkEventExceptionHandler.Adapter> getExceptionHandlerAdapters() {
        return exceptionHandlerAdapters;
    }

    private void adaptExceptionHandler(ArkEventSubscriber subscriber) {
        for (ArkEventExceptionHandler.Adapter handlerAdapter : exceptionHandlerAdapters) {
            ArkEventExceptionHandler handler = handlerAdapter.adapt(subscriber);
            if (handler != null) {
                subscriberExceptionHandlerMap.put(subscriber, handler);
                break;
            }
        }
    }

    public void addPublishSorter(Collection<? extends ArkEventPublishSorter> sorters) {
        for (ArkEventPublishSorter sorter : sorters) {
            if (sorter == null) {
                throw new ArkEventException("ArkEventPublishSorter is null");
            }
        }
        publishSorters.addAll(sorters);
        publishSorters.sort(Comparator.comparing(Order::order, Comparator.reverseOrder()));
    }

    public void addPublishSorter(ArkEventPublishSorter sorter) {
        if (sorter == null) {
            throw new ArkEventException("ArkEventPublishSorter is null");
        }
        publishSorters.add(sorter);
        publishSorters.sort(Comparator.comparing(Order::order, Comparator.reverseOrder()));
    }

    public List<ArkEventPublishSorter> getPublishSorters() {
        return publishSorters;
    }

    public void addPublishListener(Collection<? extends ArkEventPublishListener> listeners) {
        for (ArkEventPublishListener listener : listeners) {
            if (listener == null) {
                throw new ArkEventException("ArkEventPublishListener is null");
            }
        }
        this.publishListeners.addAll(listeners);
    }

    public void addPublishListener(ArkEventPublishListener listener) {
        if (listener == null) {
            throw new ArkEventException("ArkEventPublishListener is null");
        }
        this.publishListeners.add(listener);
    }

    public List<ArkEventPublishListener> getPublishListeners() {
        return publishListeners;
    }

    @Override
    public void dispatch(ArkEvent event, Object... args) {
        for (ArkEventPublishListener publishListener : publishListeners) {
            publishListener.onPublishStarted(event, args);
        }

        List<ArkEventSubscriber> filterSubscribers = new ArrayList<>();
        for (ArkEventSubscriber subscriber : subscribers) {
            List<ArkEventConditionFilter> filters = subscriberConditionFilterMap.getOrDefault(subscriber, Collections.emptyList());
            if (filterSubscriber(filters, subscriber, event, args)) {
                filterSubscribers.add(subscriber);
                for (ArkEventPublishListener publishListener : publishListeners) {
                    publishListener.onEachSubscriberConditionsFiltered(true, subscriber, filters, event, args);
                }
            } else {
                for (ArkEventPublishListener publishListener : publishListeners) {
                    publishListener.onEachSubscriberConditionsFiltered(false, subscriber, filters, event, args);
                }
            }
        }

        for (ArkEventPublishListener publishListener : publishListeners) {
            publishListener.onSubscribersFiltered(filterSubscribers, event, args);
        }

        List<ArkEventPublisherImpl> publishers = new ArrayList<>();
        for (ArkEventSubscriber filterSubscriber : filterSubscribers) {
            ArkEventPublishStrategy strategy = subscriberPublishStrategyMap.get(filterSubscriber);
            if (strategy == null) {
                throw new ArkEventException("No execute strategy found for " + filterSubscriber);
            }

            for (ArkEventPublishListener publishListener : publishListeners) {
                publishListener.onEachSubscriberPublishStrategyAdapted(strategy, filterSubscriber, event, args);
            }

            ArkEventExceptionHandler handler = subscriberExceptionHandlerMap.get(filterSubscriber);
            if (handler == null) {
                throw new ArkEventException("No exception handler found for " + filterSubscriber);
            }

            for (ArkEventPublishListener publishListener : publishListeners) {
                publishListener.onEachSubscriberExceptionHandlerAdapted(handler, filterSubscriber, event, args);
            }

            publishers.add(new ArkEventPublisherImpl(filterSubscriber, strategy, handler));
        }

        for (ArkEventPublishListener publishListener : publishListeners) {
            publishListener.onPublishersCreated(publishers, event, args);
        }

        for (ArkEventPublishSorter publishSorter : publishSorters) {
            publishers.sort((o1, o2) -> {
                boolean order1 = publishSorter.highOrder(o1.getStrategy(), o1.getSubscriber(), event, args);
                boolean order2 = publishSorter.highOrder(o2.getStrategy(), o2.getSubscriber(), event, args);
                return (order1 ? 0 : 1) - (order2 ? 0 : 1);
            });
        }

        /*if (!publishSorters.isEmpty()) {
            Comparator<ArkEventPublisherImpl> comparator = null;
            for (int i = 0; i < publishSorters.size(); i++) {
                ArkEventPublishSorter publishSorter = publishSorters.get(i);
                Comparator<ArkEventPublisherImpl> c = (o1, o2) -> {
                    boolean order1 = publishSorter.highOrder(o1.getStrategy(), o1.getSubscriber(), event, args);
                    boolean order2 = publishSorter.highOrder(o2.getStrategy(), o2.getSubscriber(), event, args);
                    return (order1 ? 0 : 1) - (order2 ? 0 : 1);
                };
                if (i == 0) {
                    comparator = c;
                } else {
                    comparator.thenComparing(c);
                }
            }
            publishers.sort(comparator);
        }*/

        for (ArkEventPublishListener publishListener : publishListeners) {
            publishListener.onPublishersSorted(publishers, event, args);
        }

        for (ArkEventPublisher publisher : publishers) {
            publisher.publish(event, args);
        }

        for (ArkEventPublishListener publishListener : publishListeners) {
            publishListener.onPublishFinished(event, args);
        }
    }

    private boolean filterSubscriber(Collection<ArkEventConditionFilter> filters,
                                     ArkEventSubscriber subscriber,
                                     ArkEvent event, Object... args) {
        if (filters == null) {
            return false;
        }
        for (ArkEventConditionFilter filter : filters) {
            if (!filter.filter(subscriber, event, args)) {
                return false;
            }
        }
        return true;
    }
}
