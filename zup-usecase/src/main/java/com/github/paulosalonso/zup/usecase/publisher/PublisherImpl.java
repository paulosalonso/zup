package com.github.paulosalonso.zup.usecase.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static java.util.Collections.emptyList;

public class PublisherImpl implements Publisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherImpl.class);

    private Map<Class, List<Consumer>> globalConsumers = new HashMap<>();

    @Override
    public void registerConsumer(Consumer consumer, Class consumedType) {
        List<Consumer> consumers = globalConsumers
                .computeIfAbsent(consumedType, k -> new ArrayList<>());

        consumers.add(consumer);
    }

    @Override
    public <T> void publish(T value) {
        if (value != null) {
            List<Consumer> consumers = globalConsumers.getOrDefault(value.getClass(), emptyList());
            consumers.forEach(consumer -> safePublish(consumer, value));
        }
    }

    private <T> void safePublish(Consumer<T> consumer, T value) {
        try {
            consumer.accept(value);
        } catch (Exception e) {
            LOGGER.error("An error occurred when publishing value to consumer {}", consumer.toString(), e);
        }
    }

}
