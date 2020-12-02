package com.github.linyuzai.arkevent.mq.rabbit.impl;

import com.github.linyuzai.arkevent.mq.ArkMqEventModuleIdProvider;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class ApplicationNameArkMqEventModuleIdProvider implements ArkMqEventModuleIdProvider, EnvironmentAware {

    private String applicationName;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    @Override
    public String getModuleId() {
        return applicationName;
    }

    @Override
    public void setEnvironment(Environment environment) {
        applicationName = environment.getProperty("spring.application.name");
    }
}
