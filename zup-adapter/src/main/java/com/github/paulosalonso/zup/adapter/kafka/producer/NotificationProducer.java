package com.github.paulosalonso.zup.adapter.kafka.producer;

import com.github.paulosalonso.notifier.kafka.avro.Notification;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;

public class NotificationProducer {

    private final KafkaTemplate<String, Notification> kafkaTemplate;
    private final String topic;

    public NotificationProducer(KafkaTemplate<String, Notification> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void send(Notification notification) {
        ProducerRecord<String, Notification> record = new ProducerRecord<>(topic, notification);
        kafkaTemplate.send(record);
    }
}
