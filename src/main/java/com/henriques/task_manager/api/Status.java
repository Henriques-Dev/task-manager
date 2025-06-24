package com.henriques.task_manager.api;

public enum Status {

    Done("Done"),
    InProgress("InProgress"),
    ToDo("To Do");

    private String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
