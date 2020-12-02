package com.github.linyuzai.arkevent.autoconfigure.configuration;

import com.github.linyuzai.arkevent.autoconfigure.transaction.StringArkEventTransactionManager;
import com.github.linyuzai.arkevent.core.*;
import com.github.linyuzai.arkevent.transaction.impl.handler.exception.TransactionArkEventExceptionHandler;
import com.github.linyuzai.arkevent.transaction.impl.handler.exception.TransactionArkEventExceptionHandlerAdapter;
import com.github.linyuzai.arkevent.transaction.impl.sorter.publish.TransactionArkEventPublishSorter;
import com.github.linyuzai.arkevent.transaction.impl.strategy.publish.TransactionArkEventPublishStrategyAdapter;
import com.github.linyuzai.arkevent.transaction.manager.ArkEventTransactionManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@ConditionalOnProperty(name = "ark-event.transaction.enabled", havingValue = "true", matchIfMissing = true)
public class ArkEventTransactionAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ArkEventTransactionManager.class)
    public StringArkEventTransactionManager stringArkEventTransactionManager() {
        return new StringArkEventTransactionManager();
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
}
