package com.github.linyuzai.arkevent.support;

import com.github.linyuzai.arkevent.core.ArkEventDispatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArkEventPlugin {

    private static ArkEventDispatcher dispatcher;

    public static void setDispatcher(ArkEventDispatcher dispatcher) {
        ArkEventPlugin.dispatcher = dispatcher;
    }

    public static ArkEventDispatcher getDispatcher() {
        return dispatcher;
    }

    public static Object[] addArgs(Object[] args, Object... addArgs) {
        List<Object> newArgs = new ArrayList<>();
        newArgs.addAll(Arrays.asList(args));
        newArgs.addAll(Arrays.asList(addArgs));
        return newArgs.toArray();
    }

    private static final FromRemote REMOTE_ARGS = new FromRemote() {
    };

    public static Object remoteArgs() {
        return REMOTE_ARGS;
    }

    public static boolean isFromRemote(Object... args) {
        long count = Arrays.stream(args).filter(it -> it instanceof FromRemote).count();
        return count > 0;
    }

    public interface FromRemote {

    }

    private static final MqTransaction MQ_TRANSACTION_ARGS = new MqTransaction() {
    };

    public static Object mqTransactionArgs() {
        return MQ_TRANSACTION_ARGS;
    }

    public static boolean isMqTransaction(Object... args) {
        long count = Arrays.stream(args).filter(it -> it instanceof MqTransaction).count();
        return count > 0;
    }

    public interface MqTransaction {

    }
}
