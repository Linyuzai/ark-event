package com.github.linyuzai.arkevent.mq.rabbit.impl;

import com.github.linyuzai.arkevent.remote.ArkEventModuleIdProvider;
import org.springframework.beans.factory.annotation.Value;

public class ApplicationNameArkEventModuleIdProvider implements ArkEventModuleIdProvider {

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public String getModuleId() {
        return applicationName;
    }
}
