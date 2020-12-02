package com.github.linyuzai.arkevent.transaction.manager;

import com.github.linyuzai.arkevent.core.ArkEvent;

public interface ArkEventTransactionManager {

    ArkEventTransactionManager NO_TRANSACTION = (arkEvent, args) -> false;

    boolean isInTransaction(ArkEvent arkEvent, Object... args);
}
