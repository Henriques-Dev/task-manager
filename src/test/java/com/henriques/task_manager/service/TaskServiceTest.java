package com.henriques.task_manager.service;

import com.henriques.task_manager.api.Priority;
import com.henriques.task_manager.api.Status;
import com.henriques.task_manager.api.TaskDto;
import com.henriques.task_manager.convert.TaskConvert;
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
        TaskDto taskDTO = new TaskDto();
        TaskEntity taskEntity = new TaskEntity();

        when(taskConvert.convertTaskDtoToTaskEntity(taskDTO)).thenReturn(taskEntity);
        taskService.saveTask(taskDTO);

        verify(taskConvert, times(1)).convertTaskDtoToTaskEntity(taskDTO);
        verify(taskRepository, times(1)).save(taskEntity);
    }

    @Test
    public void getTaskByIdTest() {

        UUID id = UUID.randomUUID();
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(id);

        when(taskRepository.findById(id)).thenReturn(Optional.of(taskEntity));
        TaskDto taskDTO = new TaskDto();
        when(taskConvert.convertTaskEntityToTaskDto(taskEntity)).thenReturn(taskDTO);

        TaskDto taskDtoResult = taskService.getTaskById(id);

        assertNotNull(taskDtoResult);
        assertEquals(taskDTO, taskDtoResult);

        verify(taskRepository, times(1)).findById(id);
        verify(taskConvert, times(1)).convertTaskEntityToTaskDto(taskEntity);

    }

    @Test
    public void deleteTaskTest() {
        UUID id = UUID.randomUUID();
        taskService.deleteTask(id);

        verify(taskRepository, times(1)).deleteById(id);
    }

    @Test
    public void updateTaskTest() {
        TaskDto taskDTO = new TaskDto();
        Instant date = Instant.parse("2023-08-21T12:00:00Z");

        taskDTO.setId(UUID.randomUUID());
        taskDTO.setTitle("Test Task Updated");
        taskDTO.setExpireOn(date);
        taskDTO.setUpdatedOn(date);
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

        TaskDto dto1 = new TaskDto();
        TaskDto dto2 = new TaskDto();
        when(taskConvert.convertTaskEntityToTaskDto(task1)).thenReturn(dto1);
        when(taskConvert.convertTaskEntityToTaskDto(task2)).thenReturn(dto2);

        List<TaskDto> taskDtoList = taskService.getAllTasks();

        assertNotNull(taskDtoList);
        assertEquals(2, taskDtoList.size());

        verify(taskRepository, times(1)).findAllByOrderByCreatedOnDesc();
        verify(taskConvert, times(2)).convertTaskEntityToTaskDto(any(TaskEntity.class));
    }

}
