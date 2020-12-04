package com.github.linyuzai.arkevent.mq;

import com.github.linyuzai.arkevent.core.ArkEvent;

import java.util.Map;

public interface ArkMqEventIdempotentManager {

    String getEventId(ArkEvent event, Map<Object, Object> args);

    boolean isEventHandled(String eventId, ArkMqEventDecoder decoder, Object o);

    void onEventRepeated(String eventId, ArkMqEventDecoder decoder, Object o);
}
