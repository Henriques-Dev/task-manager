package com.henriques.task_manager.api;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class TaskDTO {


    private UUID id;
    @NotEmpty(message = "Tittle should not be empty")
    private String title;
    private Instant createdOn;
    private Instant updateOn;
    @NotNull(message = "Expire should not be empty, please, set a date")
    private Instant expiredOn;
    private Priority priority;
    private Status status;
    @NotEmpty(message = "Description should not be empty")
    private String description;

    public TaskDTO() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskDTO)) return false;
        TaskDTO taskDTO = (TaskDTO) o;
        return Objects.equals(id, taskDTO.id) &&
                Objects.equals(title, taskDTO.title) &&
                Objects.equals(createdOn, taskDTO.createdOn) &&
                Objects.equals(updateOn, taskDTO.updateOn) &&
                Objects.equals(expiredOn, taskDTO.expiredOn) &&
                priority == taskDTO.priority &&
                status == taskDTO.status &&
                Objects.equals(description, taskDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, createdOn, updateOn, expiredOn, priority, status, description);
    }

}