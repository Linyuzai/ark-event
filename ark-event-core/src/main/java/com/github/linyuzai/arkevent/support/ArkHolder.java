package com.github.linyuzai.arkevent.support;

import com.github.linyuzai.arkevent.ArkEventDispatcher;

public class ArkHolder {

    private static ArkEventDispatcher dispatcher;

    public static void setDispatcher(ArkEventDispatcher dispatcher) {
        ArkHolder.dispatcher = dispatcher;
    }

    public static ArkEventDispatcher getDispatcher() {
        return dispatcher;
    }
}
