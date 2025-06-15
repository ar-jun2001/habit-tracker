package com.habit.tracker.repository;

import com.habit.tracker.entity.HabitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HabitRepository extends JpaRepository<HabitEntity, Long> {
    Optional<HabitEntity> findById(Long id);
}
