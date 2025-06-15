package com.habit.tracker.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "permissions")
public class PermissonEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column(name = "permission_name", nullable = false, unique = true)
    private String permissionName; // e.g., "READ", "WRITE", "DELETE"
    @Column(name = "description")
    private String description; // Description of the permission
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true; // Default to active
    @Column(name = "created_date_time", nullable = false)
    private LocalDateTime createdDateTime; // Timestamp of when the permission was created
    @Column(name = "updated_date_time", nullable = true)
    private LocalDateTime updatedDateTime; // Timestamp of when the permission was last updated

}
