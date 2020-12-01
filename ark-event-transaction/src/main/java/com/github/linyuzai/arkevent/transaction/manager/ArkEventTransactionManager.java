package com.github.linyuzai.arkevent.transaction.manager;

import com.github.linyuzai.arkevent.core.ArkEvent;

public interface ArkEventTransactionManager {

    boolean isInTransaction(ArkEvent arkEvent, Object... args);
}
