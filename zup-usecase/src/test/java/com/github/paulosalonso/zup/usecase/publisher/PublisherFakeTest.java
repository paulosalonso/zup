package com.github.paulosalonso.zup.usecase.publisher;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static com.github.paulosalonso.zup.usecase.LoggerHelper.getListAppender;
import static org.assertj.core.api.Assertions.assertThat;

public class PublisherFakeTest {

    private PublisherFake publisherFake = new PublisherFake();

    @Test
    public void whenRegisterConsumerThenLog() {
        ListAppender<ILoggingEvent> appender = getListAppender();

        PrintStream out = System.out;

        publisherFake.registerConsumer(out::println, String.class);

        assertThat(appender.list)
                .hasSize(1)
                .first()
                .satisfies(event -> assertThat(event.getFormattedMessage())
                        .isEqualTo("Attempt to register consumer for type String"));
    }

    @Test
    public void whenPublishThenLog() {
        ListAppender<ILoggingEvent> appender = getListAppender();

        publisherFake.publish("Test");

        assertThat(appender.list)
                .hasSize(1)
                .first()
                .satisfies(event -> assertThat(event.getFormattedMessage())
                        .isEqualTo("Attempt to publish value of type String"));
    }

}
