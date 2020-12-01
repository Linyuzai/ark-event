package com.github.linyuzai.arkevent.autoconfigure.encoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.mq.ArkMqEventEncoder;

import java.util.HashMap;
import java.util.Map;

public class JacksonMqEventEncoder implements ArkMqEventEncoder {

    private ObjectMapper objectMapper;

    public JacksonMqEventEncoder() {
        this(new ObjectMapper());
    }

    public JacksonMqEventEncoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Object encode(ArkEvent event) throws Throwable {
        Map<String, Object> map = new HashMap<>();
        map.put("className", event.getClass().getName());
        map.put("content", event);
        return objectMapper.writeValueAsString(map);
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
