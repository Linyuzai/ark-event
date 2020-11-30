package com.github.linyuzai.arkevent;

import com.github.linyuzai.arkevent.basic.ArkEventDispatcher;

public class ArkEventPlugin {

    private static ArkEventDispatcher dispatcher;

    public static void setDispatcher(ArkEventDispatcher dispatcher) {
        ArkEventPlugin.dispatcher = dispatcher;
    }

    public static ArkEventDispatcher getDispatcher() {
        return dispatcher;
    }
}
