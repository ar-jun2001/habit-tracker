package com.habit.tracker.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.habit.tracker.entity.HabitEntity;
import com.habit.tracker.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/habits-tracker")
public class HabitController {

    @Autowired
    private HabitService habitService;

    @GetMapping("/habit-tracker")
    public String openHabitTrackerPage() {
        return "index"; // Loads index.html from templates
    }

    @PostMapping("save")
    public JsonNode saveHabit(@RequestBody HabitEntity habitEntity) {
        try {
            JsonNode savedHabit = habitService.saveHabit(habitEntity);
            if (savedHabit != null) {
                return JsonNodeFactory.instance.objectNode()
                        .put("status", "success")
                        .put("message", "Habit saved successfully")
                        .put("habitName", savedHabit.get("habitName").asText());
            } else {
                return JsonNodeFactory.instance.objectNode()
                        .put("status", "error")
                        .put("message", "Failed to save habit");
            }
        } catch (Exception e) {
            return JsonNodeFactory.instance.objectNode()
                    .put("status", "error")
                    .put("message", e.getMessage());
        }
    }

    @GetMapping("getAllHabits")
    public List<HabitEntity> getAllHabits() {
        try {
            if (!habitService.getAllHabits().isEmpty()) {
                return habitService.getAllHabits();
            } else {
                return List.of();
            }
        } catch (Exception e) {
            System.out.println("Error fetching habits: " + e.getMessage());
        }
        return List.of();
    }

    @PostMapping("updateHabitStatus")
    public JsonNode updateHabitStatus(@RequestBody HabitEntity habitEntity) {
        try {
            JsonNode updatedHabit = habitService.updateHabitStatus(habitEntity.getId(), habitEntity.getHabitStatus());
            if (updatedHabit != null) {
                return JsonNodeFactory.instance.objectNode()
                        .put("status", "success")
                        .put("message", "Habit updated successfully")
                        .put("habitName", updatedHabit.get("habitName").asText());
            } else {
                return JsonNodeFactory.instance.objectNode()
                        .put("status", "error")
                        .put("message", "Failed to update habit");
            }
        } catch (Exception e) {
            return JsonNodeFactory.instance.objectNode()
                    .put("status", "error")
                    .put("message", e.getMessage());
        }
    }

    @PostMapping("deleteHabit")
    public JsonNode deleteHabit(@RequestBody JsonNode id) {
        try {
            long idValue = id.get("id").asLong();
            JsonNode result = habitService.deleteHabit(idValue);
            if (result == null || (result.has("status") && result.get("status").asText().equals("error"))) {
                return JsonNodeFactory.instance.objectNode()
                        .put("status", "error")
                        .put("message", "Habit not found with id: " + idValue);
            }
            return result;
        } catch (Exception e) {
            return JsonNodeFactory.instance.objectNode()
                    .put("status", "error")
                    .put("message", e.getMessage());
        }
    }
}
