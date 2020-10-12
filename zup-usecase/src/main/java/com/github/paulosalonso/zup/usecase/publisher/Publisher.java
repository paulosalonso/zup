package com.github.paulosalonso.zup.usecase.publisher;

import java.util.function.Consumer;

public interface Publisher {
    void registerConsumer(Consumer consumer, Class consumedType);

    <T> void publish(T value);
}
