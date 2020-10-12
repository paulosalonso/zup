package com.github.paulosalonso.zup.usecase.publisher;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class PublisherFakeTest {

    private PublisherFake publisherFake = new PublisherFake();

    @Test
    public void whenRegisterConsumerThenLog() {
        ListAppender<ILoggingEvent> appender = buildAppender();

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
        ListAppender<ILoggingEvent> appender = buildAppender();

        publisherFake.publish("Test");

        assertThat(appender.list)
                .hasSize(1)
                .first()
                .satisfies(event -> assertThat(event.getFormattedMessage())
                        .isEqualTo("Attempt to publish value of type String"));
    }

    private ListAppender<ILoggingEvent> buildAppender() {
        Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        ListAppender<ILoggingEvent> appender = new ListAppender<>();
        appender.start();
        logger.addAppender(appender);
        return appender;
    }

}
