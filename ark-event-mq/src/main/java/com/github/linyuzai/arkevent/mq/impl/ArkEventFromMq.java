package com.github.linyuzai.arkevent.mq.impl;

import com.github.linyuzai.arkevent.remote.ArkEventFromRemote;

public class ArkEventFromMq implements ArkEventFromRemote {

    private static final ArkEventFromMq args = new ArkEventFromMq();

    public static ArkEventFromMq args() {
        return args;
    }

    private ArkEventFromMq() {

    }
}
