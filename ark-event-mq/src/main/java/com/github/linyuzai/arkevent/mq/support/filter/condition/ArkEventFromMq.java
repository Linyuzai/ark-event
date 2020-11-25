package com.github.linyuzai.arkevent.mq.support.filter.condition;

import com.github.linyuzai.arkevent.ArkEventFromRemote;

public class ArkEventFromMq implements ArkEventFromRemote {

    private static final ArkEventFromMq mask = new ArkEventFromMq();

    public static ArkEventFromMq mask() {
        return mask;
    }

    private ArkEventFromMq() {

    }
}
