package com.habit.tracker.config;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class KafaConsumerRoute extends RouteBuilder {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void configure() throws Exception {
        from("kafka:habit-events?brokers=localhost:9092")
            .routeId("kafkaConsumerRoute")
            .log("Received message: ${body}")
            .process(exchange -> {
                String message = exchange.getIn().getBody(String.class);
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setTo("user@example.com"); // TODO: set recipient dynamically if needed
                mail.setSubject("Habit Created/Updated");
                mail.setText("Notification: " + message);
                mailSender.send(mail);
            });
    }
}
