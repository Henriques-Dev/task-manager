package com.henriques.task_manager.service;

import com.henriques.task_manager.api.Priority;
import com.henriques.task_manager.api.Status;
import com.henriques.task_manager.model.TaskEntity;
import com.henriques.task_manager.repository.TaskRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostConstruct
    private void generateRandomTask() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle("Tarefa Task");
        taskEntity.setDescription("Tarefa teste.");
        taskEntity.setStatus(Status.InProgress);
        taskEntity.setPriority(Priority.High);
        taskEntity.setUpdateOn(Instant.now());
        taskEntity.setExpiredOn(Instant.now());
        taskEntity.setCreatedOn(Instant.now());

        saveTask(taskEntity);

    }

    public void saveTask(TaskEntity taskEntity) {
        taskRepository.save(taskEntity);
    }
}
