package com.github.linyuzai.arkevent.mq;

import java.util.Map;

public interface ArkMqEventIdempotentManager {

    String getEventId(Object event, Map<Object, Object> args);

    boolean isEventHandled(String eventId, ArkMqEventDecoder decoder, Object o);

    void onEventRepeated(String eventId, ArkMqEventDecoder decoder, Object o);
}
