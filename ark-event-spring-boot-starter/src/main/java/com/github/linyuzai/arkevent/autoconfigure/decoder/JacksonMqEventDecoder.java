package com.github.linyuzai.arkevent.autoconfigure.decoder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.mq.ArkMqEventDecoder;

public class JacksonMqEventDecoder implements ArkMqEventDecoder {

    private ObjectMapper objectMapper;

    public JacksonMqEventDecoder() {
        this(new ObjectMapper());
    }

    public JacksonMqEventDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ArkEvent decode(Object event) throws Throwable {
        JsonNode node = objectMapper.readTree((String) event);
        String className = node.get("className").asText();
        String content = node.get("content").toString();
        return (ArkEvent) objectMapper.readValue(content, Class.forName(className));
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
