package com.github.linyuzai.arkevent.mq.impl.manager.idempotent;

import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.mq.ArkMqEventDecoder;
import com.github.linyuzai.arkevent.mq.ArkMqEventIdempotentManager;

public abstract class AnnotationArkMqEventIdempotentManager implements ArkMqEventIdempotentManager {

    public static final String EVENT_IDEMPOTENT_IGNORED = "ArkMqEvent@IdempotentIgnored";

    @Override
    public String getEventId(ArkEvent event, Object... args) {
        if (event.getClass().isAnnotationPresent(EventIdempotent.class)) {
            return getIdempotentEventId(event, args);
        }
        return EVENT_IDEMPOTENT_IGNORED;
    }

    @Override
    public boolean isEventHandled(String eventId, ArkMqEventDecoder decoder, Object o) {
        if (EVENT_IDEMPOTENT_IGNORED.equals(eventId)) {
            return false;
        }
        return isIdempotentEventHandled(eventId, decoder, o);
    }

    public abstract String getIdempotentEventId(ArkEvent event, Object... args);

    public abstract boolean isIdempotentEventHandled(String eventId, ArkMqEventDecoder decoder, Object o);
}
