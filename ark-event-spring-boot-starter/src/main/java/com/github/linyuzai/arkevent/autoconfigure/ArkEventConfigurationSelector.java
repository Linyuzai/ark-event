package com.github.linyuzai.arkevent.autoconfigure;

import com.github.linyuzai.arkevent.autoconfigure.configuration.ArkEventAutoConfiguration;
import com.github.linyuzai.arkevent.autoconfigure.configuration.ArkEventTransactionAutoConfiguration;
import com.github.linyuzai.arkevent.autoconfigure.configuration.ArkMqEventAutoConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArkEventConfigurationSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> map = importingClassMetadata.getAnnotationAttributes(EnableArkEvent.class.getName());
        boolean event = map != null;
        boolean mq = map != null && (boolean) map.getOrDefault("mq", false);
        boolean transaction = map != null && (boolean) map.getOrDefault("transaction", false);
        List<String> names = new ArrayList<>();
        if (event) {
            names.add(ArkEventAutoConfiguration.class.getName());
            if (mq) {
                names.add(ArkMqEventAutoConfiguration.class.getName());
                if (!transaction) {
                    names.add(ArkMqEventAutoConfiguration.TransactionAutoConfiguration.class.getName());
                }
            }
            if (transaction) {
                names.add(ArkEventTransactionAutoConfiguration.class.getName());
            }
        }
        return names.toArray(new String[0]);
    }
}
