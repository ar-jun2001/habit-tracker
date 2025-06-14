package com.habit.tracker.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "habits")
@Data
public class HabitEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "habit_name", nullable = false)
    private String habitName;
    @Column(name = "habit_description" , nullable = true)
    private String habitDescription;
    @Column(name ="habit_Status", nullable = false)
    private String habitStatus;

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHabitDescription() {
        return habitDescription;
    }

    public void setHabitDescription(String habitDescription) {
        this.habitDescription = habitDescription;
    }

    public String getHabitStatus() {
        return habitStatus;
    }

    public void setHabitStatus(String habitStatus) {
        this.habitStatus = habitStatus;
    }
}
