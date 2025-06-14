package com.habit.tracker.repository;

import com.habit.tracker.entity.HabitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<HabitEntity, Long> {
}
