package com.henriques.task_manager.convert;

import com.henriques.task_manager.api.TaskDTO;
import com.henriques.task_manager.model.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskConvert {

    public TaskEntity convertTaskDtoToTaskEntity(TaskDTO taskDTO) {
        TaskEntity taskEntity = new TaskEntity();

        taskEntity.setTitle(taskDTO.getTitle());
        taskEntity.setStatus(taskDTO.getStatus());
        taskEntity.setCreatedOn(taskDTO.getCreatedOn());
        taskEntity.setExpiredOn(taskDTO.getExpiredOn());
        taskEntity.setDescription(taskDTO.getDescription());
        taskEntity.setPriority(taskDTO.getPriority());
        taskEntity.setUpdateOn(taskDTO.getUpdateOn());
        return taskEntity;
    }

    public TaskDTO convertTaskEntityToTaskDto(TaskEntity taskEntity) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(taskEntity.getId());
        taskDTO.setTitle(taskEntity.getTitle());
        taskDTO.setStatus(taskEntity.getStatus());
        taskDTO.setCreatedOn(taskEntity.getCreatedOn());
        taskDTO.setExpiredOn(taskEntity.getExpiredOn());
        taskDTO.setDescription(taskEntity.getDescription());
        taskDTO.setPriority(taskEntity.getPriority());
        taskDTO.setUpdateOn(taskEntity.getUpdateOn());
        return taskDTO;
    }
}
