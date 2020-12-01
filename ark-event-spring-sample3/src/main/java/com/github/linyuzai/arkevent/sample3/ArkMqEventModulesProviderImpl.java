package com.github.linyuzai.arkevent.sample3;

import com.github.linyuzai.arkevent.mq.ArkMqEventModulesProvider;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArkMqEventModulesProviderImpl implements ArkMqEventModulesProvider {

    @Override
    public String[] getModules() {
        return new String[]{"ark-event-sample1", "ark-event-sample2", "ark-event-sample3"};
    }
}
