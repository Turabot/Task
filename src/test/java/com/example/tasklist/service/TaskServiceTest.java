package com.example.tasklist.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.tasklist.dto.TaskDto;
import com.example.tasklist.entity.TaskEntity;
import com.example.tasklist.enums.TaskStatus;
import com.example.tasklist.exception.NotFoundException;
import com.example.tasklist.mapper.TaskMapper;
import com.example.tasklist.repository.TaskRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TaskServiceTest {

  @InjectMocks
  private TaskService taskService;

  @Mock
  private TaskRepository taskRepository;

  @Mock
  private TaskMapper mapper;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testCreateTask() {
    var now = LocalDateTime.now();
    var taskEntity = new TaskEntity();
    taskEntity.setDescription("Test Description");
    taskEntity.setStatus(TaskStatus.NEW);
    taskEntity.setCreatedDate(now);
    taskEntity.setUpdatedDate(now);

    when(taskRepository.save(any(TaskEntity.class))).thenReturn(taskEntity);

    taskService.createTask("Test Description");

    verify(taskRepository, times(1)).save(any(TaskEntity.class));
  }

  @Test
  void testGetTaskById() {
    long taskId = 1L;
    var taskEntity = new TaskEntity();
    taskEntity.setId(taskId);

    when(taskRepository.findById(taskId)).thenReturn(Optional.of(taskEntity));

    TaskEntity result = taskService.getTaskById(taskId);

    assertNotNull(result);
    assertEquals(taskId, result.getId());
  }

  @Test
  void testGetTaskByIdNotFound() {
    var taskId = 1L;

    when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> taskService.getTaskById(taskId));
  }

  @Test
  void testGetAllTasks() {
    var now = LocalDateTime.now();
    var taskEntity = new TaskEntity();
    when(taskRepository.findAll()).thenReturn(List.of(taskEntity));

    var taskDto = new TaskDto(1L, TaskStatus.NEW, "Test Description", now, now);
    var act = List.of(taskDto);

    when(mapper.from(taskEntity)).thenReturn(taskDto);

    var result = taskService.getAllTasks();

    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(result, act);
  }

  @Test
  void testUpdateTask() {
    var taskId = 1L;
    var taskEntity = new TaskEntity();
    taskEntity.setId(taskId);
    when(taskRepository.findById(taskId)).thenReturn(Optional.of(taskEntity));

    var newDescription = "Updated Description";
    var newStatus = TaskStatus.IN_PROGRESS;

    taskService.updateTask(taskId, newDescription, newStatus);

    assertEquals(newDescription, taskEntity.getDescription());
    assertEquals(newStatus, taskEntity.getStatus());
    assertNotNull(taskEntity.getUpdatedDate());
  }

  @Test
  void testDeleteTask() {
    var taskId = 1L;
    when(taskRepository.existsById(taskId)).thenReturn(true);

    taskService.deleteTask(taskId);

    verify(taskRepository, times(1)).deleteById(taskId);
  }

  @Test
  void testDeleteTaskNotFound() {
    var taskId = 1L;
    when(taskRepository.existsById(taskId)).thenReturn(false);

    assertThrows(NotFoundException.class, () -> taskService.deleteTask(taskId));
  }
}

