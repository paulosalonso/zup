package com.github.paulosalonso.zup.usecase.publisher;

import java.util.*;
import java.util.function.Consumer;

import static java.util.Collections.emptyList;

public class PublisherImpl implements Publisher {

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
            consumers.forEach(consumer -> consumer.accept(value));
        }
    }

}