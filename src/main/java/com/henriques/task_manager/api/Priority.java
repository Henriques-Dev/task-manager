package com.henriques.task_manager.api;

public enum Priority {
    High("High"),
    Normal("Normal"),
    Low("Low");

    private String description;

    Priority(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
