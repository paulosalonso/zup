package com.github.paulosalonso.zup.usecase.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class PublisherFake implements Publisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherFake.class);

    @Override
    public void registerConsumer(Consumer consumer, Class consumedType, String consumerName) {
        LOGGER.info("Attempt to register consumer called '{}' for type {}", consumerName, consumedType.getSimpleName());
    }

    @Override
    public <T> void publish(T value) {
        LOGGER.info("Attempt to publish value of type {}", value.getClass().getSimpleName());
    }
}
