package com.github.linyuzai.arkevent.autoconfigure.encoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventEncoder;

import java.util.HashMap;
import java.util.Map;

public class JacksonMqEventEncoder extends RabbitArkMqEventEncoder {

    private ObjectMapper objectMapper;

    public JacksonMqEventEncoder() {
        this(new ObjectMapper());
    }

    public JacksonMqEventEncoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public byte[] encodeEvent(Object event) throws Throwable {
        Map<String, Object> map = new HashMap<>();
        map.put("className", event.getClass().getName());
        map.put("content", event);
        String string = objectMapper.writeValueAsString(map);
        if (string == null) {
            return new byte[0];
        } else {
            return string.getBytes();
        }
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
