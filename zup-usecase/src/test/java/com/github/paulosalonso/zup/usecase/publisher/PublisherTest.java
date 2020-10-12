package com.github.paulosalonso.zup.usecase.publisher;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.function.Consumer;

import static org.mockito.Mockito.*;

public class PublisherTest {

    private Publisher publisher = new Publisher();

    @Test
    public void givenAConsumerWhenPublishAConsumedTypeThenAccept() {
        PrintStream out = spy(System.out);
        Consumer<String> consumer = out::println;

        publisher.registerConsumer(consumer, String.class);
        publisher.publish("Test");

        verify(out).println("Test");
    }

    @Test
    public void givenAConsumerWhenPublishANonConsumedTypeThenDoNothing() {
        PrintStream out = spy(System.out);
        Consumer<String> consumer = out::println;

        publisher.registerConsumer(consumer, String.class);
        publisher.publish(1);

        verifyNoInteractions(out);
    }

}
