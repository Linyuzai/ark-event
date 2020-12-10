package com.github.linyuzai.arkevent.support;

import com.github.linyuzai.arkevent.core.ArkEventPublisher;

import java.util.Map;

public class ArkEventPlugin {

    private static boolean mqEnabled = false;

    private static boolean transactionEnabled = false;

    public static boolean isMqEnabled() {
        return mqEnabled;
    }

    public static void setMqEnabled(boolean mqEnabled) {
        ArkEventPlugin.mqEnabled = mqEnabled;
    }

    public static boolean isTransactionEnabled() {
        return transactionEnabled;
    }

    public static void setTransactionEnabled(boolean transactionEnabled) {
        ArkEventPlugin.transactionEnabled = transactionEnabled;
    }

    private static ArkEventPublisher publisher;

    public static void setPublisher(ArkEventPublisher publisher) {
        ArkEventPlugin.publisher = publisher;
    }

    public static ArkEventPublisher getPublisher() {
        return publisher;
    }

    public static final String HEADER_EVENT_ID = "h-ark-event-event-id";

    public static final String ARGS_REMOTE_KEY = "x-ark-event-remote";

    public static boolean isRemote(Map args) {
        return args.containsKey(ARGS_REMOTE_KEY);
    }

    public static final String ARGS_MQ_TRANSACTION_KEY = "x-ark-event-mq-transaction";

    public static boolean isMqTransaction(Map args) {
        return args.containsKey(ARGS_MQ_TRANSACTION_KEY);
    }

    public static final String ARGS_REMOTE_MESSAGE_EXCEPTION_KEY = "x-ark-event-remote-message-exception";

    public static boolean isRemoteMessageException(Map args) {
        return args.containsKey(ARGS_REMOTE_MESSAGE_EXCEPTION_KEY);
    }
}
