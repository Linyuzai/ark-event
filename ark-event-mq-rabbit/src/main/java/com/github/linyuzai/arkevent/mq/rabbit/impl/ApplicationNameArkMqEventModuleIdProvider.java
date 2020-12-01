package com.github.linyuzai.arkevent.mq.rabbit.impl;

import com.github.linyuzai.arkevent.mq.ArkMqEventModuleIdProvider;
import org.springframework.beans.factory.annotation.Value;

public class ApplicationNameArkMqEventModuleIdProvider implements ArkMqEventModuleIdProvider {

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public String getModuleId() {
        return applicationName;
    }
}
