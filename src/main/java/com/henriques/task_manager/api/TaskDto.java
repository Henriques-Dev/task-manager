package com.henriques.task_manager.api;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class TaskDto {


    private UUID id;
    @NotEmpty(message = "Tittle should not be empty")
    private String title;
    private Instant createdOn;
    private Instant updatedOn;
    @NotNull(message = "Expire should not be empty, please, set a date")
    private Instant expireOn;
    private Priority priority;
    private Status status;
    @NotEmpty(message = "Description should not be empty")
    private String description;

    public TaskDto() {
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

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Instant getExpireOn() {
        return expireOn;
    }

    public void setExpireOn(Instant expireOn) {
        this.expireOn = expireOn;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskDto)) return false;
        TaskDto taskDTO = (TaskDto) o;
        return Objects.equals(id, taskDTO.id) &&
                Objects.equals(title, taskDTO.title) &&
                Objects.equals(createdOn, taskDTO.createdOn) &&
                Objects.equals(updatedOn, taskDTO.updatedOn) &&
                Objects.equals(expireOn, taskDTO.expireOn) &&
                priority == taskDTO.priority &&
                status == taskDTO.status &&
                Objects.equals(description, taskDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, createdOn, updatedOn, expireOn, priority, status, description);
    }

}