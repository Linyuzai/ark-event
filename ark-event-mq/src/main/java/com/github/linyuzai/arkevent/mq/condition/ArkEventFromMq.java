package com.github.linyuzai.arkevent.mq.condition;

import com.github.linyuzai.arkevent.ArkEventFromAny;

public class ArkEventFromMq implements ArkEventFromAny {

    private static final ArkEventFromMq mask = new ArkEventFromMq();

    public static ArkEventFromMq mask() {
        return mask;
    }

    private ArkEventFromMq() {

    }
}
