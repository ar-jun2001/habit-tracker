package com.habit.tracker.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;
    @Column(name = "description")
    private String description;
    @Column(name = "permissions")
    private String permissions; // Comma-separated list of permissions, e.g., "READ,WRITE,DELETE"
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true; // Default to active
    @Column(name = "created_date_time", nullable = false)
    private LocalDateTime createdDateTime; // Timestamp of when the role was created
    @Column(name = "updated_date_time", nullable = true)
    private LocalDateTime updatedDateTime; // Timestamp of when the role was last updated

}
