package com.baskaran.task.mappers;

import com.baskaran.task.dto.TaskDto;
import com.baskaran.task.model.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);
}
