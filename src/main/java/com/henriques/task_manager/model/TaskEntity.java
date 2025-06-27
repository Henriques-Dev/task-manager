package com.henriques.task_manager.model;

import com.henriques.task_manager.api.Priority;
import com.henriques.task_manager.api.Status;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "task", schema = "schema_task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;
    @Column(name="title")
    private String title;
    @Column(name="created_on")
    private Instant createdOn;
    @Column(name="update_on")
    private Instant updateOn;
    @Column(name="expired_on")
    private Instant expiredOn;
    @Column(name="priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name="description", columnDefinition = "TEXT", length = 300)
    private String description;

    public TaskEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(Instant updateOn) {
        this.updateOn = updateOn;
    }

    public Instant getExpiredOn() {
        return expiredOn;
    }

    public void setExpiredOn(Instant expiredOn) {
        this.expiredOn = expiredOn;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
