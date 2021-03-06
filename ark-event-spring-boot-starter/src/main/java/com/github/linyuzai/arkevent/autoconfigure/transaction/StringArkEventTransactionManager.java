package com.github.linyuzai.arkevent.autoconfigure.transaction;

import com.github.linyuzai.arkevent.transaction.manager.ArkEventTransactionManager;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Map;

public class StringArkEventTransactionManager implements ArkEventTransactionManager {

    @Override
    public boolean isInTransaction(Object arkEvent, Map<Object, Object> args) {
        return TransactionSynchronizationManager.isActualTransactionActive();
    }
}
