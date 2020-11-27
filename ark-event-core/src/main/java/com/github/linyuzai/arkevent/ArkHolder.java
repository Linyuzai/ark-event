package com.github.linyuzai.arkevent;

import com.github.linyuzai.arkevent.basic.ArkEventDispatcher;

public class ArkHolder {

    private static ArkEventDispatcher dispatcher;

    public static void setDispatcher(ArkEventDispatcher dispatcher) {
        ArkHolder.dispatcher = dispatcher;
    }

    public static ArkEventDispatcher getDispatcher() {
        return dispatcher;
    }
}
