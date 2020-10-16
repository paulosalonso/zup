package com.github.paulosalonso.zup.usecase.publisher;

import java.util.function.Consumer;

public interface Publisher {
    void registerConsumer(Consumer consumer, Class consumedType, String consumerName);
    <T> void publish(T value);
}
