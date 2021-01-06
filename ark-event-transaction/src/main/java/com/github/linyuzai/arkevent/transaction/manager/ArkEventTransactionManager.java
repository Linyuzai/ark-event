package com.github.linyuzai.arkevent.transaction.manager;

import java.util.Map;

public interface ArkEventTransactionManager {

    ArkEventTransactionManager NO_TRANSACTION = (arkEvent, args) -> false;

    boolean isInTransaction(Object event, Map<Object, Object> args);
}
