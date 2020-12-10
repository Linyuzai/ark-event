package com.github.linyuzai.arkevent.autoconfigure.selector;

import com.github.linyuzai.arkevent.autoconfigure.ArkEventAutoConfiguration;
import com.github.linyuzai.arkevent.autoconfigure.ArkEventTransactionAutoConfiguration;
import com.github.linyuzai.arkevent.autoconfigure.ArkMqEventAutoConfiguration;
import com.github.linyuzai.arkevent.autoconfigure.EnableArkEvent;
import com.github.linyuzai.arkevent.support.ArkEventPlugin;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArkEventConfigurationSelector implements DeferredImportSelector {

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
                ArkEventPlugin.setMqEnabled(true);
                names.add(ArkMqEventAutoConfiguration.class.getName());
                if (!transaction) {
                    names.add(ArkMqEventAutoConfiguration.TransactionAutoConfiguration.class.getName());
                }
            }
            if (transaction) {
                ArkEventPlugin.setTransactionEnabled(true);
                names.add(ArkEventTransactionAutoConfiguration.class.getName());
            }
        }
        return names.toArray(new String[0]);
    }
}
