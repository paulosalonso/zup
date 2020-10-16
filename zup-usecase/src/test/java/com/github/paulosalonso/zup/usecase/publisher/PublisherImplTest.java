package com.github.paulosalonso.zup.usecase.publisher;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.function.Consumer;

import static com.github.paulosalonso.zup.usecase.LoggerHelper.getListAppender;
import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    public void givenSeveralConsumersWhenPublishAndAnyoneThrowsAnExceptionThenPublishToTheOthers() {
        ListAppender<ILoggingEvent> appender = getListAppender();

        Consumer<String> consumerA = mock(Consumer.class);
        Consumer<String> consumerB = mock(Consumer.class);

        doThrow(RuntimeException.class).when(consumerA).accept("Test");

        publisherImpl.registerConsumer(consumerA, String.class);
        publisherImpl.registerConsumer(consumerB, String.class);

        publisherImpl.publish("Test");

        verify(consumerA).accept("Test");
        verify(consumerB).accept("Test");

        assertThat(appender.list)
                .hasSize(1)
                .first().satisfies(event -> {
                    assertThat(event.getLevel()).isEqualTo(Level.ERROR);
                    assertThat(event.getLoggerName()).isEqualTo(publisherImpl.getClass().getName());
                });
    }

}
