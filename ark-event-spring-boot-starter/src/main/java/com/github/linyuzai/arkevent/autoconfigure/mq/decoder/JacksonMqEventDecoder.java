package com.github.linyuzai.arkevent.autoconfigure.mq.decoder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.linyuzai.arkevent.basic.ArkEvent;
import com.github.linyuzai.arkevent.mq.ArkMqEventDecoder;

public class JacksonMqEventDecoder implements ArkMqEventDecoder<String> {

    private ObjectMapper objectMapper;

    public JacksonMqEventDecoder() {
        this(new ObjectMapper());
    }

    public JacksonMqEventDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ArkEvent decode(String event) throws Throwable {
        JsonNode node = objectMapper.readTree(event);
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
