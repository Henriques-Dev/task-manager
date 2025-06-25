package com.henriques.task_manager.service;

import com.henriques.task_manager.api.TaskDTO;
import com.henriques.task_manager.mapper.TaskConvert;
import com.henriques.task_manager.model.TaskEntity;
import com.henriques.task_manager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskConvert taskConvert;
    @InjectMocks
    private TaskService taskService;

    public TaskServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveTaskTest() {
        TaskDTO taskDTO = new TaskDTO();
        TaskEntity taskEntity = new TaskEntity();

        when(taskConvert.convert(taskDTO)).thenReturn(taskEntity);
        taskService.saveTask(taskDTO);

        verify(taskConvert, times(1)).convert(taskDTO);
        verify(taskRepository, times(1)).save(taskEntity);
    }

}
