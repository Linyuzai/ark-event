package com.github.linyuzai.arkevent.autoconfigure;

import com.github.linyuzai.arkevent.ArkEventException;
import com.github.linyuzai.arkevent.basic.*;
import com.github.linyuzai.arkevent.basic.impl.ArkEventDispatcherImpl;
import com.github.linyuzai.arkevent.ArkEventPlugin;
import com.github.linyuzai.arkevent.basic.impl.filter.condition.group.GroupArkEventConditionFilterFactory;
import com.github.linyuzai.arkevent.basic.impl.filter.condition.type.TypeArkEventConditionFilterFactory;
import com.github.linyuzai.arkevent.basic.impl.handler.exception.logger.Slf4jArkEventExceptionHandlerAdapter;
import com.github.linyuzai.arkevent.basic.impl.handler.exception.rethrow.RethrowArkEventExceptionHandlerAdapter;
import com.github.linyuzai.arkevent.basic.impl.strategy.publish.SimpleArkEventPublishStrategyAdapter;
import com.github.linyuzai.arkevent.transaction.ArkEventTransactionManager;
import com.github.linyuzai.arkevent.transaction.handler.exception.TransactionArkEventExceptionHandler;
import com.github.linyuzai.arkevent.transaction.handler.exception.TransactionArkEventExceptionHandlerAdapter;
import com.github.linyuzai.arkevent.transaction.sorter.publish.TransactionArkEventPublishSorter;
import com.github.linyuzai.arkevent.transaction.strategy.publish.TransactionArkEventPublishStrategyAdapter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class ArkEventAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(TypeArkEventConditionFilterFactory.class)
    public TypeArkEventConditionFilterFactory typeArkEventConditionFilterFactory() {
        return new TypeArkEventConditionFilterFactory();
    }

    @Bean
    @ConditionalOnMissingBean(GroupArkEventConditionFilterFactory.class)
    public GroupArkEventConditionFilterFactory groupArkEventConditionFilterFactory() {
        return new GroupArkEventConditionFilterFactory();
    }

    @Bean
    @ConditionalOnMissingBean(SimpleArkEventPublishStrategyAdapter.class)
    public SimpleArkEventPublishStrategyAdapter directArkEventPublishStrategyAdapter() {
        return new SimpleArkEventPublishStrategyAdapter();
    }

    @Bean
    @ConditionalOnMissingBean(RethrowArkEventExceptionHandlerAdapter.class)
    public RethrowArkEventExceptionHandlerAdapter rethrowArkEventExceptionHandlerAdapter() {
        return new RethrowArkEventExceptionHandlerAdapter();
    }

    @Bean
    @ConditionalOnMissingBean(Slf4jArkEventExceptionHandlerAdapter.class)
    public Slf4jArkEventExceptionHandlerAdapter slf4jArkEventExceptionHandlerAdapter() {
        return new Slf4jArkEventExceptionHandlerAdapter();
    }

    @Bean
    @ConditionalOnMissingBean(ArkEventTransactionManager.class)
    public ArkEventTransactionManager arkEventTransactionManager() {
        return () -> false;
    }

    @Bean
    @ConditionalOnMissingBean(TransactionArkEventPublishStrategyAdapter.class)
    public TransactionArkEventPublishStrategyAdapter transactionArkEventPublishStrategyAdapter(ArkEventTransactionManager transactionManager) {
        return new TransactionArkEventPublishStrategyAdapter(transactionManager);
    }

    @Bean
    @ConditionalOnMissingBean(TransactionArkEventExceptionHandler.class)
    public TransactionArkEventExceptionHandler transactionArkEventExceptionHandler() {
        return new TransactionArkEventExceptionHandler() {
            @Override
            public void handleTransactionException(ArkEventException ex) throws Throwable {

            }
        };
    }

    @Bean
    @ConditionalOnMissingBean(TransactionArkEventExceptionHandlerAdapter.class)
    public TransactionArkEventExceptionHandlerAdapter transactionArkEventExceptionHandlerAdapter(TransactionArkEventExceptionHandler handler) {
        return new TransactionArkEventExceptionHandlerAdapter(handler);
    }

    @Bean
    @ConditionalOnMissingBean(TransactionArkEventPublishSorter.class)
    public TransactionArkEventPublishSorter transactionArkEventPublishSorter() {
        return new TransactionArkEventPublishSorter();
    }

    @Bean
    @ConditionalOnMissingBean(ArkEventDispatcher.class)
    public ArkEventDispatcherImpl arkEventDispatcher(List<ArkEventSubscriber> subscribers,
                                                     List<ArkEventPublishListener> publishListeners,
                                                     List<ArkEventConditionFilter.Factory> conditionFilterFactories,
                                                     List<ArkEventPublishStrategy.Adapter> publishStrategyAdapters,
                                                     List<ArkEventExceptionHandler.Adapter> exceptionHandlerAdapters) {
        ArkEventDispatcherImpl dispatcher = new ArkEventDispatcherImpl();
        dispatcher.addConditionFilterFactory(conditionFilterFactories);
        dispatcher.addPublishStrategyAdapter(publishStrategyAdapters);
        dispatcher.addExceptionHandlerAdapter(exceptionHandlerAdapters);
        dispatcher.addPublishListener(publishListeners);
        dispatcher.registerSubscriber(subscribers);
        ArkEventPlugin.setDispatcher(dispatcher);
        return dispatcher;
    }
}
