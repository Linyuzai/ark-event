package com.github.linyuzai.arkevent.support;

public interface ArkOrder {

    int PUBLISH_STRATEGY_ADAPTER_REMOTE = -2;
    int PUBLISH_STRATEGY_ADAPTER_ASYNC = -1;

    int EXCEPTION_HANDLER_ADAPTER_RETHROW = -1;

    default int order() {
        return 0;
    }
}
