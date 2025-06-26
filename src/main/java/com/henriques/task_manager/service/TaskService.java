package com.henriques.task_manager.service;

import com.henriques.task_manager.api.TaskDto;
import com.henriques.task_manager.exceptions.TaskNotFoundException;
import com.henriques.task_manager.convert.TaskConvert;
import com.henriques.task_manager.model.TaskEntity;
import com.henriques.task_manager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskConvert taskConvert;

    public TaskService(TaskRepository taskRepository, TaskConvert taskConvert) {
        this.taskRepository = taskRepository;
        this.taskConvert = taskConvert;
    }

    public void saveTask(TaskDto taskDTO) {

        try {
            TaskEntity taskEntity = taskConvert.convertTaskDtoToTaskEntity(taskDTO);
            taskRepository.save(taskEntity);
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao salvar a tarefa: " + e.getMessage());
        }
    }

    public TaskDto getTaskById(UUID id) {
        Optional<TaskEntity> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {
            return taskConvert.convertTaskEntityToTaskDto(optionalTask.get());
        } else {
            throw new TaskNotFoundException("Task with ID " + id + " not found.");
        }
    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }

    public void updateTask(TaskDto taskDTO) {
        try {
            Optional<TaskEntity> optionalTask = taskRepository.findById(taskDTO.getId());

            if (optionalTask.isPresent()) {
                TaskEntity taskEntity = optionalTask.get();

                taskEntity.setTitle(taskDTO.getTitle());
                taskEntity.setDescription(taskDTO.getDescription());
                taskEntity.setPriority(taskDTO.getPriority());
                taskEntity.setStatus(taskDTO.getStatus());
                taskEntity.setExpiredOn(taskDTO.getExpireOn());
                taskEntity.setUpdateOn(Instant.now());

                taskRepository.save(taskEntity);
            } else {
                throw new TaskNotFoundException("Task with ID " + taskDTO.getId() + " not found.");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TaskDto> getAllTasks() {
        return taskRepository.findAllByOrderByCreatedOnDesc()
                .stream()
                .map(taskConvert::convertTaskEntityToTaskDto)
                .collect(Collectors.toList());
    }
}
