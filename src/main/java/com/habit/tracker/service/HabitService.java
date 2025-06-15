package com.habit.tracker.service;

import com.habit.tracker.config.KafaConsumerRoute;
import com.habit.tracker.config.KafaProducerRoute;
import com.habit.tracker.entity.HabitEntity;
import com.habit.tracker.repository.HabitRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitService {
    @Autowired
    private HabitRepository habitRepository;
    @Autowired
    private KafaProducerRoute kafaProducerRoute;

    public JsonNode saveHabit(HabitEntity habitEntity) {
        if (habitEntity == null || habitEntity.getHabitName() == null || habitEntity.getHabitName().isEmpty()) {
            return JsonNodeFactory.instance.objectNode()
                    .put("status", "error")
                    .put("message", "Habit name cannot be null or empty");
        }
        if (habitEntity.getHabitStatus() == null || habitEntity.getHabitStatus().isEmpty()) {
            return JsonNodeFactory.instance.objectNode()
                    .put("status", "error")
                    .put("message", "Habit status cannot be null or empty");
        }
        if (habitEntity.getHabitDescription() == null || habitEntity.getHabitDescription().isEmpty()) {
            return JsonNodeFactory.instance.objectNode()
                    .put("status", "error")
                    .put("message", "Habit description cannot be null or empty");
        }
        try {
            HabitEntity savedHabit;
            if (habitEntity.getId() != null && habitEntity.getId() > 0) {
                savedHabit = editHabitEntity(habitEntity);
                return JsonNodeFactory.instance.objectNode()
                        .put("status", "success")
                        .put("message", "Habit updated successfully")
                        .put("habitName", savedHabit.getHabitName());
            }
            savedHabit = habitRepository.save(habitEntity);
            kafaProducerRoute.sendMessageToKafka(savedHabit);
            return JsonNodeFactory.instance.objectNode()
                    .put("status", "success")
                    .put("message", "Habit saved successfully")
                    .put("habitName", savedHabit.getHabitName());

        } catch (Exception e) {
            return JsonNodeFactory.instance.objectNode()
                    .put("status", "error")
                    .put("message", e.getMessage());
        }
    }

    private HabitEntity editHabitEntity(HabitEntity habitEntity) {
        HabitEntity existingHabit = habitRepository.findById(habitEntity.getId())
                .orElseThrow(() -> new IllegalArgumentException("Habit not found with id: " + habitEntity.getId()));
        existingHabit.setHabitName(habitEntity.getHabitName());
        existingHabit.setHabitStatus(habitEntity.getHabitStatus());
        existingHabit.setHabitDescription(habitEntity.getHabitDescription());
        // Add more fields here if needed
        return habitRepository.save(existingHabit);
    }

    public List<HabitEntity> getAllHabits() {
        List<HabitEntity> habits = habitRepository.findAll();
        if (habits.isEmpty()) {
            return List.of();
        }
        return habits;
    }

    public JsonNode deleteHabit(Long id) {
        if (id == null || id <= 0) {
            return JsonNodeFactory.instance.objectNode()
                    .put("status", "error")
                    .put("message", "Invalid habit ID");
        }
        try {

            if (!habitRepository.existsById(id)) {
                return JsonNodeFactory.instance.objectNode()
                        .put("status", "error")
                        .put("message", "Habit not found with id: " + id);
            }
            habitRepository.deleteById(id);
            return JsonNodeFactory.instance.objectNode()
                    .put("status", "success")
                    .put("message", "Habit deleted successfully");
        } catch (Exception e) {
            return JsonNodeFactory.instance.objectNode()
                    .put("status", "error")
                    .put("message", e.getMessage());
        }
    }

    public JsonNode updateHabitStatus(Long id, String status) {
        if (id == null || id <= 0) {
            return JsonNodeFactory.instance.objectNode()
                    .put("status", "error")
                    .put("message", "Invalid habit ID");
        }
        if (status == null || status.isEmpty()) {
            return JsonNodeFactory.instance.objectNode()
                    .put("status", "error")
                    .put("message", "Habit status cannot be null or empty");
        }
        try {
            HabitEntity habitEntity = habitRepository.findById(id).get();
            if (habitEntity == null) {
                return JsonNodeFactory.instance.objectNode()
                        .put("status", "error")
                        .put("message", "Habit not found with id: " + id);
            }
            habitEntity.setHabitStatus(status);
            habitRepository.save(habitEntity);
            return JsonNodeFactory.instance.objectNode()
                    .put("habitName", habitEntity.getHabitName())
                    .put("id", habitEntity.getId());
        } catch (Exception e) {
            return JsonNodeFactory.instance.objectNode()
                    .put("status", "error")
                    .put("message", e.getMessage());
        }
    }
}
