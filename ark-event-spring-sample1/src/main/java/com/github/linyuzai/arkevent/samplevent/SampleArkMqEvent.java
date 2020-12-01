package com.github.linyuzai.arkevent.samplevent;

import com.github.linyuzai.arkevent.core.ArkEvent;

public class SampleArkMqEvent implements ArkEvent {

    private String sample;

    public SampleArkMqEvent() {
    }

    public SampleArkMqEvent(String sample) {
        this.sample = sample;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    @Override
    public String toString() {
        return "SampleArkMqEvent{" +
                "sample='" + sample + '\'' +
                '}';
    }
}
