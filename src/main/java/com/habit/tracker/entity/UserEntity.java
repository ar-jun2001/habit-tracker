package com.habit.tracker.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "role", nullable = false)
    private String role; // e.g., "USER", "ADMIN"
    @Column(name = "user_disable", nullable = false)
    private boolean userDisable = false;
    @Column(name = "pwd_expirty_date", nullable = true)
    private String pwdExpirtyDate; // Optional field for password expiry date
    @Column(name = "pwd_change_date", nullable = true)
    private LocalDateTime pwdChangeDate; // Optional field for password change date
    @Column(name = "user_is_locked", nullable = false)
    private boolean userIsLocked = false; // Default to not locked
//    @OneToMany
//    @JoinColumn(name = "habit_id", referencedColumnName = "id")
//    private List<HabitEntity> habits;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPwdExpirtyDate() {
        return pwdExpirtyDate;
    }

    public void setPwdExpirtyDate(String pwdExpirtyDate) {
        this.pwdExpirtyDate = pwdExpirtyDate;
    }

    public boolean isUserDisable() {
        return userDisable;
    }

    public void setUserDisable(boolean userDisable) {
        this.userDisable = userDisable;
    }

    public LocalDateTime getPwdChangeDate() {
        return pwdChangeDate;
    }

    public void setPwdChangeDate(LocalDateTime pwdChangeDate) {
        this.pwdChangeDate = pwdChangeDate;
    }

    public boolean isUserIsLocked() {
        return userIsLocked;
    }

    public void setUserIsLocked(boolean userIsLocked) {
        this.userIsLocked = userIsLocked;
    }

//    public List<HabitEntity> getHabits() {
//        return habits;
//    }
//
//    public void setHabits(List<HabitEntity> habits) {
//        this.habits = habits;
//    }
}

