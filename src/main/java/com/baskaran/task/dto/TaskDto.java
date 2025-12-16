package com.baskaran.task.dto;

import com.baskaran.task.model.TaskPriority;
import com.baskaran.task.model.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}
