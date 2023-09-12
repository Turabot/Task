package com.example.tasklist.dto;

import com.example.tasklist.enums.TaskStatus;
import java.time.LocalDateTime;

public record TaskDto(Long id, TaskStatus status, String description, LocalDateTime createdDate, LocalDateTime updatedDate) {
}
