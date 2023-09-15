package com.example.tasklist.service;

import com.example.tasklist.dto.TaskDto;
import com.example.tasklist.entity.TaskEntity;
import com.example.tasklist.enums.TaskStatus;
import com.example.tasklist.exception.NotFoundException;
import com.example.tasklist.mapper.TaskMapper;
import com.example.tasklist.repository.TaskRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;
  private final TaskMapper mapper;

  public void createTask(String description) {
    var now = LocalDateTime.now();
    var taskEntity = new TaskEntity();
    taskEntity.setDescription(description);
    taskEntity.setStatus(TaskStatus.NEW);
    taskEntity.setCreatedDate(now);
    taskEntity.setUpdatedDate(now);
    taskRepository.save(taskEntity);
  }

  public TaskEntity getTaskById(long taskId) {
    return taskRepository.findById(taskId)
        .orElseThrow(() -> new NotFoundException("Task with identifier " + taskId + " not found"));
  }

  public List<TaskDto> getAllTasks() {
    return taskRepository.findAll().stream()
        .map(mapper::from)
        .toList();
  }

  @Transactional
  public void updateTask(long taskId, String description, TaskStatus status) {
    var taskEntity = getTaskById(taskId);

    if (description != null) {
      taskEntity.setDescription(description);
    }

    if (status != null) {
      taskEntity.setStatus(status);
    }

    taskEntity.setUpdatedDate(LocalDateTime.now());
  }

  public void deleteTask(long taskId) {
    log.info("Delete task by id: '{}'", taskId);
    var exists = taskRepository.existsById(taskId);
    if (!exists) {
      throw new NotFoundException("Task with identifier " + taskId + " not found");
    }
    taskRepository.deleteById(taskId);
  }
}
