package com.github.linyuzai.arkevent.mq;

import com.github.linyuzai.arkevent.core.ArkEvent;

public interface ArkMqEventIdempotentHandler {

    String getEventId(ArkEvent event, Object... args);

    boolean isEventHandled(String eventId, ArkMqEventDecoder decoder, Object o);

    void onEventRepeated(String eventId, ArkMqEventDecoder decoder, Object o);
}
