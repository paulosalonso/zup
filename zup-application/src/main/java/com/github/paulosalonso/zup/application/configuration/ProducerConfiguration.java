package com.github.paulosalonso.zup.application.configuration;

import com.github.paulosalonso.notifier.kafka.avro.Notification;
import com.github.paulosalonso.zup.adapter.kafka.producer.NotificationProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class ProducerConfiguration {

    @Profile("customer-creation-notifier")
    @Bean
    public NotificationProducer notificationProducer(KafkaTemplate<String, Notification> kafkaTemplate,
            @Value("${com.github.paulosalonso.zup.kafka.topic.customer-created-notifier}") String topic) {
        return new NotificationProducer(kafkaTemplate, topic);
    }
}
