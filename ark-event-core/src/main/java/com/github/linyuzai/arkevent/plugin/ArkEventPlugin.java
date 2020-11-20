package com.github.linyuzai.arkevent.plugin;

import com.github.linyuzai.arkevent.ArkEventDispatcher;

public class ArkEventPlugin {

    private static ArkEventDispatcher dispatcher;

    public static void setDispatcher(ArkEventDispatcher dispatcher) {
        ArkEventPlugin.dispatcher = dispatcher;
    }

    public static ArkEventDispatcher getDispatcher() {
        return dispatcher;
    }
}
