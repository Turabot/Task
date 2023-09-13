package com.example.tasklist.controller;

import com.example.tasklist.dto.TaskDto;
import com.example.tasklist.entity.TaskEntity;
import com.example.tasklist.enums.TaskStatus;
import com.example.tasklist.service.TaskService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

  private final TaskService taskService;

  @PostMapping("/create")
  public void createTask(@RequestParam String description) {
    taskService.createTask(description);
  }

  @GetMapping("/{taskId}")
  public TaskEntity getTaskById(@PathVariable long taskId) {
    return taskService.getTaskById(taskId);
  }

  @GetMapping("/all")
  public List<TaskDto> getAllTasks() {
    return taskService.getAllTasks();
  }

  @PatchMapping("/{taskId}")
  public void updateTask(
      @PathVariable long taskId,
      @RequestParam(required = false) String description,
      @RequestParam(required = false) TaskStatus status) {
    taskService.updateTask(taskId, description, status);
  }

  @DeleteMapping("/{taskId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteTask(@PathVariable long taskId) {
    taskService.deleteTask(taskId);
  }
}
