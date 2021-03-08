package com.github.linyuzai.arkevent.support;

public interface Order {

    int CONDITION_FILTER_EXPRESSION = 10;

    int PUBLISH_STRATEGY_ADAPTER_TRANSACTION = -20;
    int PUBLISH_STRATEGY_ADAPTER_MQ = -10;
    int PUBLISH_STRATEGY_ADAPTER_SIMPLE = 0;

    int EXCEPTION_HANDLER_ADAPTER_TRANSACTION = -30;
    int EXCEPTION_HANDLER_ADAPTER_MQ = -20;
    int EXCEPTION_HANDLER_ADAPTER_RETHROW = -10;
    int EXCEPTION_HANDLER_ADAPTER_LOGGER = 0;

    int PUBLISH_SORTER_TRANSACTION = -20;
    int PUBLISH_SORTER_MQ = -10;

    default int order() {
        return 0;
    }
}
