package com.github.paulosalonso.zup.usecase.publisher;

import java.util.function.Consumer;

public interface Publisher {
    <T> void registerConsumer(Consumer<T> consumer, Class<T> consumedType, String consumerName);
    <T> void publish(T value);
}
