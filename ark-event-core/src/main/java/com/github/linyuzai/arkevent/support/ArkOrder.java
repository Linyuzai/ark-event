package com.github.linyuzai.arkevent.support;

public interface ArkOrder {

    int PUBLISH_STRATEGY_ADAPTER_REMOTE = -30;
    int PUBLISH_STRATEGY_ADAPTER_TRANSACTION = -20;
    int PUBLISH_STRATEGY_ADAPTER_ASYNC = -10;
    int PUBLISH_STRATEGY_ADAPTER_DIRECT = 0;

    int EXCEPTION_HANDLER_ADAPTER_TRANSACTION = -20;
    int EXCEPTION_HANDLER_ADAPTER_RETHROW = -10;
    int EXCEPTION_HANDLER_ADAPTER_LOGGER = 0;

    int PUBLISH_SORTER_TRANSACTION = -30;
    int PUBLISH_SORTER_REMOTE = -20;
    int PUBLISH_SORTER_ASYNC = -10;

    default int order() {
        return 0;
    }
}
