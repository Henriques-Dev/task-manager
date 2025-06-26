package com.henriques.task_manager.convert;

import com.henriques.task_manager.api.TaskDto;
import com.henriques.task_manager.model.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskConvert {

    public TaskEntity convertTaskDtoToTaskEntity(TaskDto taskDTO) {
        TaskEntity taskEntity = new TaskEntity();

        taskEntity.setTitle(taskDTO.getTitle());
        taskEntity.setStatus(taskDTO.getStatus());
        taskEntity.setCreatedOn(taskDTO.getCreatedOn());
        taskEntity.setExpiredOn(taskDTO.getExpireOn());
        taskEntity.setDescription(taskDTO.getDescription());
        taskEntity.setPriority(taskDTO.getPriority());
        taskEntity.setUpdateOn(taskDTO.getUpdatedOn());
        return taskEntity;
    }

    public TaskDto convertTaskEntityToTaskDto(TaskEntity taskEntity) {
        TaskDto taskDTO = new TaskDto();
        taskDTO.setId(taskEntity.getId());
        taskDTO.setTitle(taskEntity.getTitle());
        taskDTO.setStatus(taskEntity.getStatus());
        taskDTO.setCreatedOn(taskEntity.getCreatedOn());
        taskDTO.setExpireOn(taskEntity.getExpiredOn());
        taskDTO.setDescription(taskEntity.getDescription());
        taskDTO.setPriority(taskEntity.getPriority());
        taskDTO.setUpdatedOn(taskEntity.getUpdateOn());
        return taskDTO;
    }
}
