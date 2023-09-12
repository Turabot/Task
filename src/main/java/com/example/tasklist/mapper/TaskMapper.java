package com.example.tasklist.mapper;

import com.example.tasklist.dto.TaskDto;
import com.example.tasklist.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

  public TaskDto from(TaskEntity entity) {
   return new TaskDto(entity.getId(), entity.getStatus(), entity.getDescription(), entity.getCreatedDate(), entity.getUpdatedDate());
  }
}
