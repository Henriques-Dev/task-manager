package com.henriques.task_manager.service;

import com.henriques.task_manager.api.Priority;
import com.henriques.task_manager.api.Status;
import com.henriques.task_manager.api.TaskDTO;
import com.henriques.task_manager.mapper.TaskConvert;
import com.henriques.task_manager.model.TaskEntity;
import com.henriques.task_manager.repository.TaskRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskConvert taskConvert;

    public TaskService(TaskRepository taskRepository, TaskConvert taskConvert) {
        this.taskRepository = taskRepository;
        this.taskConvert = taskConvert;
    }

    @PostConstruct
    private void generateRandomTask() {
        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setTitle("Aula de violão");
        taskDTO.setDescription("Praticar escala maior");
        taskDTO.setStatus(Status.InProgress);
        taskDTO.setPriority(Priority.Low);
        taskDTO.setUpdateOn(Instant.now());
        taskDTO.setExpiredOn(Instant.now());
        taskDTO.setCreatedOn(Instant.now());

        saveTask(taskDTO);

    }

    public void saveTask(TaskDTO taskDTO) {

        try {
            TaskEntity taskEntity = taskConvert.convert(taskDTO);
            taskRepository.save(taskEntity);
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao salvar a tarefa: " + e.getMessage());
        }
    }
}
