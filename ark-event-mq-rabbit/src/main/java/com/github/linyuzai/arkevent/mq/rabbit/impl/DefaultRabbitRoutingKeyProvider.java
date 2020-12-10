package com.github.linyuzai.arkevent.mq.rabbit.impl;

import com.github.linyuzai.arkevent.mq.ArkMqEventModuleIdProvider;
import com.github.linyuzai.arkevent.mq.ArkMqEventModulesProvider;
import com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventRoutingKeyProvider;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DefaultRabbitRoutingKeyProvider implements RabbitArkMqEventRoutingKeyProvider {

    private Set<String> routingKeys = new HashSet<>();

    private String routingKey;

    public DefaultRabbitRoutingKeyProvider(ArkMqEventModuleIdProvider idProvider, ArkMqEventModulesProvider moduleProvider) {
        String lowerCaseModule = idProvider.getModuleId().toLowerCase();
        this.routingKey = getRoutingKey0(lowerCaseModule);
        for (String module : moduleProvider.getModules()) {
            String lowerCase = module.toLowerCase();
            if (!lowerCase.equals(lowerCaseModule)) {
                routingKeys.add(getRoutingKey0(lowerCase));
            }
        }
    }

    @Override
    public Collection<String> getRoutingKeys() {
        return routingKeys;
    }

    @Override
    public String getRoutingKey() {
        return routingKey;
    }

    private String getRoutingKey0(String service) {
        return "ark-event." + service;
    }
}
