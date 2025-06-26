package com.henriques.task_manager.service;

import com.henriques.task_manager.api.Priority;
import com.henriques.task_manager.api.Status;
import com.henriques.task_manager.api.TaskDTO;
import com.henriques.task_manager.mapper.TaskConvert;
import com.henriques.task_manager.model.TaskEntity;
import com.henriques.task_manager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskConvert taskConvert;
    @InjectMocks
    private TaskService taskService;

    @Test
    public void saveTaskTest() {
        TaskDTO taskDTO = new TaskDTO();
        TaskEntity taskEntity = new TaskEntity();

        when(taskConvert.convert(taskDTO)).thenReturn(taskEntity);
        taskService.saveTask(taskDTO);

        verify(taskConvert, times(1)).convert(taskDTO);
        verify(taskRepository, times(1)).save(taskEntity);
    }

    @Test
    public void getTaskByIdTest() {

        UUID id = UUID.randomUUID();
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(id);

        when(taskRepository.findById(id)).thenReturn(Optional.of(taskEntity));
        TaskDTO taskDTO = new TaskDTO();
        when(taskConvert.convert(taskEntity)).thenReturn(taskDTO);

        TaskDTO taskDTOResult = taskService.getTaskById(id);

        assertNotNull(taskDTOResult);
        assertEquals(taskDTO, taskDTOResult);

        verify(taskRepository, times(1)).findById(id);
        verify(taskConvert, times(1)).convert(taskEntity);

    }

    @Test
    public void deleteTaskTest() {
        UUID id = UUID.randomUUID();
        taskService.deleteTask(id);

        verify(taskRepository, times(1)).deleteById(id);
    }

    @Test
    public void updateTaskTest() {
        TaskDTO taskDTO = new TaskDTO();
        Instant date = Instant.parse("2023-08-21T12:00:00Z");

        taskDTO.setId(UUID.randomUUID());
        taskDTO.setTitle("Test Task Updated");
        taskDTO.setExpiredOn(date);
        taskDTO.setUpdateOn(date);
        taskDTO.setCreatedOn(date);
        taskDTO.setStatus(Status.InProgress);
        taskDTO.setDescription("Test Description Updated");
        taskDTO.setPriority(Priority.High);

        TaskEntity existingTask = new TaskEntity();
        existingTask.setId(taskDTO.getId());
        existingTask.setCreatedOn(date);

        when(taskRepository.findById(taskDTO.getId()))
                .thenReturn(Optional.of(existingTask));

        taskService.updateTask(taskDTO);

        ArgumentCaptor<TaskEntity> taskEntityArgumentCaptor = ArgumentCaptor.forClass(TaskEntity.class);
        verify(taskRepository, times(1)).save(taskEntityArgumentCaptor.capture());

        TaskEntity taskSaved = taskEntityArgumentCaptor.getValue();

        assertEquals("Test Task Updated", taskSaved.getTitle());
        assertEquals("Test Description Updated", taskSaved.getDescription());
        assertEquals(Status.InProgress, taskSaved.getStatus());
        assertEquals(Priority.High, taskSaved.getPriority());
        assertEquals(date, taskSaved.getCreatedOn());
        assertEquals(date, taskSaved.getExpiredOn());
        assertNotNull(taskSaved.getUpdateOn());
    }

    @Test
    public void getTaskListTest() {

        TaskEntity task1 = new TaskEntity();
        TaskEntity task2 = new TaskEntity();
        when(taskRepository.findAllByOrderByCreatedOnDesc()).thenReturn(List.of(task1, task2));

        TaskDTO dto1 = new TaskDTO();
        TaskDTO dto2 = new TaskDTO();
        when(taskConvert.convert(task1)).thenReturn(dto1);
        when(taskConvert.convert(task2)).thenReturn(dto2);

        List<TaskDTO> taskDTOList = taskService.getAllTasks();

        assertNotNull(taskDTOList);
        assertEquals(2, taskDTOList.size());

        verify(taskRepository, times(1)).findAllByOrderByCreatedOnDesc();
        verify(taskConvert, times(2)).convert(any(TaskEntity.class));
    }

}
