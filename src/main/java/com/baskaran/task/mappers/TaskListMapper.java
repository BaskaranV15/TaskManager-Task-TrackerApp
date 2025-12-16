package com.baskaran.task.mappers;

import com.baskaran.task.dto.TaskListDto;
import com.baskaran.task.model.TaskList;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
