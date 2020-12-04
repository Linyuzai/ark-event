package com.github.linyuzai.arkevent.transaction.manager;

import com.github.linyuzai.arkevent.core.ArkEvent;

import java.util.Map;

public interface ArkEventTransactionManager {

    ArkEventTransactionManager NO_TRANSACTION = (arkEvent, args) -> false;

    boolean isInTransaction(ArkEvent arkEvent, Map<Object, Object> args);
}
