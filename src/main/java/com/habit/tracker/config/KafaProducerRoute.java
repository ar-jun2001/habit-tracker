package com.habit.tracker.config;

import com.habit.tracker.entity.HabitEntity;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafaProducerRoute  extends RouteBuilder {
    private static final String HABIT_TOPIC = "habit-events";
    @Autowired
    private ProducerTemplate template;
    @Override
    public void configure() throws Exception {
        from("timer:kafka-timer?period=5000")
                .setBody().simple("Hello from Camel at ${date:now}")
                .to("kafka:" + HABIT_TOPIC + "?brokers=localhost:9092");
    }

    public void sendMessageToKafka(HabitEntity savedHabit) {
        try {
            String message = "Habit saved: " + savedHabit.getHabitName() + ", Status: " + savedHabit.getHabitStatus();
            template.sendBody("kafka:" + HABIT_TOPIC + "?brokers=localhost:9092", message);
        } catch (Exception e) {
            System.out.println("Error sending message to Kafka: " + e.getMessage());
        }
    }
}
