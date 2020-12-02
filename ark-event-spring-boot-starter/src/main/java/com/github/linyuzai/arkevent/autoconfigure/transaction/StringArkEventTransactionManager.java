package com.github.linyuzai.arkevent.autoconfigure.transaction;

import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.transaction.manager.ArkEventTransactionManager;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class StringArkEventTransactionManager implements ArkEventTransactionManager {

    @Override
    public boolean isInTransaction(ArkEvent arkEvent, Object... args) {
        return TransactionSynchronizationManager.isActualTransactionActive();
    }
}
