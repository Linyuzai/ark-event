package com.github.linyuzai.arkevent.support;

import com.github.linyuzai.arkevent.core.ArkEventPublisher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ArkEventPlugin {

    private static ArkEventPublisher publisher;

    public static void setPublisher(ArkEventPublisher publisher) {
        ArkEventPlugin.publisher = publisher;
    }

    public static ArkEventPublisher getPublisher() {
        return publisher;
    }

    public static final String ARGS_REMOTE_KEY = "x-ark-event-remote";

    public static boolean isRemote(Map args) {
        return args.containsKey(ARGS_REMOTE_KEY);
    }

    public static final String ARGS_MQ_TRANSACTION_KEY = "x-arg-event-mq-transaction";

    public static boolean isMqTransaction(Map args) {
        return args.containsKey(ARGS_MQ_TRANSACTION_KEY);
    }
}
