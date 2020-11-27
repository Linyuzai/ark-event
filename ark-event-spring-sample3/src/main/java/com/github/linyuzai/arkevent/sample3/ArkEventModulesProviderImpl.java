package com.github.linyuzai.arkevent.sample3;

import com.github.linyuzai.arkevent.remote.ArkEventModulesProvider;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArkEventModulesProviderImpl implements ArkEventModulesProvider {

    @Override
    public String[] getModules() {
        return new String[]{"ark-event-sample1", "ark-event-sample2", "ark-event-sample3"};
    }
}
