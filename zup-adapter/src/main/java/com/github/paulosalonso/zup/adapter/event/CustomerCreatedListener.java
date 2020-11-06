package com.github.paulosalonso.zup.adapter.event;

import com.github.paulosalonso.notifier.kafka.avro.Notification;
import com.github.paulosalonso.notifier.kafka.avro.NotificationType;
import com.github.paulosalonso.zup.adapter.kafka.producer.NotificationProducer;
import com.github.paulosalonso.zup.usecase.event.CustomerCreated;
import com.github.paulosalonso.zup.usecase.publisher.Publisher;

import java.util.List;
import java.util.Map;

public class CustomerCreatedListener {

    private final NotificationProducer notificationProducer;

    public CustomerCreatedListener(Publisher publisher, NotificationProducer notificationProducer) {
        this.notificationProducer = notificationProducer;
        publisher.registerConsumer(this::listenCustomerCreated, CustomerCreated.class, "customer-created-notifier");
    }

    private void listenCustomerCreated(CustomerCreated event) {
        notificationProducer.send(buildNotification(event));
    }

    private Notification buildNotification(CustomerCreated customerCreated) {
        StringBuilder messageBuilder = new StringBuilder()
                .append("The following user has been registered in the Zup application:<br />")
                .append("ID: <strong>").append(customerCreated.getCustomer().getId()).append("</strong><br />")
                .append("Name: <strong>").append(customerCreated.getCustomer().getName()).append("</strong>");

        return Notification.newBuilder()
                .setType(NotificationType.EMAIL)
                .setSender("Zup Test Application <paulo_alonso_@hotmail.com>")
                .setRecipients(List.of("paulo_alonso_@hotmail.com"))
                .setSubject("New Customer Created on Zup Application")
                .setMessage(messageBuilder.toString())
                .setAdditionalProperties(Map.of("IS_HTML_MESSAGE", "true"))
                .build();
    }

}
