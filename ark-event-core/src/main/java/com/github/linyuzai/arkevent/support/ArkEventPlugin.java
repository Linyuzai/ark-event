package com.github.linyuzai.arkevent.support;

import com.github.linyuzai.arkevent.core.ArkEventDispatcher;

import java.util.Arrays;

public class ArkEventPlugin {

    private static ArkEventDispatcher dispatcher;

    public static void setDispatcher(ArkEventDispatcher dispatcher) {
        ArkEventPlugin.dispatcher = dispatcher;
    }

    public static ArkEventDispatcher getDispatcher() {
        return dispatcher;
    }

    private static final FromRemote REMOTE_ARGS = new FromRemote() {
    };

    public static Object remoteArgs() {
        return REMOTE_ARGS;
    }

    public static boolean isFromRemote(Object... args) {
        long count = Arrays.stream(args).filter(it -> it instanceof FromRemote).count();
        return count == 0;
    }

    public interface FromRemote {

    }
}
