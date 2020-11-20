package com.github.linyuzai.arkevent;

import com.github.linyuzai.arkevent.plugin.ArkEventPlugin;

import java.util.*;

public interface ArkEvent {

    default PublishConfiguration getPublishConfiguration() {
        return null;
    }

    default void setPublishConfiguration(PublishConfiguration configuration) {

    }

    default void publish() {
        ArkEventPlugin.getDispatcher().dispatch(this);
    }

    class PublishConfiguration {

        public static final PublishConfiguration EMPTY = new PublishConfiguration();

        private Collection<ArkEventConditionFilter> conditionFilters;
        private ArkEventPublishStrategy publishStrategy;
        private ArkEventExceptionHandler eventExceptionHandler;
        private Map<String, Object> attributes;

        public Collection<ArkEventConditionFilter> getConditionFilters() {
            return conditionFilters;
        }

        public void setConditionFilters(Collection<? extends ArkEventConditionFilter> conditionFilters) {
            if (this.conditionFilters == null) {
                this.conditionFilters = new ArrayList<>();
            }
            this.conditionFilters.addAll(conditionFilters);
        }

        public void addConditionFilter(ArkEventConditionFilter conditionFilter) {
            if (this.conditionFilters == null) {
                this.conditionFilters = new ArrayList<>();
            }
            this.conditionFilters.add(conditionFilter);
        }

        public ArkEventPublishStrategy getPublishStrategy() {
            return publishStrategy;
        }

        public void setPublishStrategy(ArkEventPublishStrategy publishStrategy) {
            this.publishStrategy = publishStrategy;
        }

        public ArkEventExceptionHandler getEventExceptionHandler() {
            return eventExceptionHandler;
        }

        public void setEventExceptionHandler(ArkEventExceptionHandler eventExceptionHandler) {
            this.eventExceptionHandler = eventExceptionHandler;
        }

        public Map<String, Object> getAttributes() {
            return attributes;
        }

        @SuppressWarnings("unchecked")
        public <T> T getAttribute(String key) {
            if (attributes == null) {
                return null;
            }
            return (T) attributes.get(key);
        }

        public void addAttribute(String key, Object value) {
            if (attributes == null) {
                attributes = new LinkedHashMap<>();
            }
            attributes.put(key, value);
        }

        public void addAttributes(Map<String, Object> attributes) {
            if (this.attributes == null) {
                this.attributes = new LinkedHashMap<>();
            }
            this.attributes.putAll(attributes);
        }
    }
}
