package com.github.paulosalonso.zup.usecase.publisher;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.function.Consumer;

import static org.mockito.Mockito.*;

public class PublisherImplTest {

    private PublisherImpl publisherImpl = new PublisherImpl();

    @Test
    public void givenAConsumerWhenPublishAConsumedTypeThenAccept() {
        PrintStream out = spy(System.out);
        Consumer<String> consumer = out::println;

        publisherImpl.registerConsumer(consumer, String.class);
        publisherImpl.publish("Test");

        verify(out).println("Test");
    }

    @Test
    public void givenAConsumerWhenPublishANonConsumedTypeThenDoNothing() {
        PrintStream out = spy(System.out);
        Consumer<String> consumer = out::println;

        publisherImpl.registerConsumer(consumer, String.class);
        publisherImpl.publish(1);

        verifyNoInteractions(out);
    }

}
