package com.baskaran.task.mappers.impl;

import com.baskaran.task.dto.TaskDto;
import com.baskaran.task.mappers.TaskMapper;
import com.baskaran.task.model.Task;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task fromDto(TaskDto taskDto) {
        Task task = new Task(); //

        task.setId(taskDto.id());
        task.setTitle(taskDto.title());
        task.setDescription(taskDto.description());
        task.setDueDate(taskDto.dueDate());
        task.setPriority(taskDto.priority());
        task.setStatus(taskDto.status());

        // system-controlled fields
        task.setCreateAt(LocalDateTime.now());
        task.setUpdateAt(LocalDateTime.now());

        // taskList should be set in Service layer
        // task.setTaskList(taskList);

        return task;
    }


    @Override
    public TaskDto toDto(Task task) {
        if (task == null) {
            return null;
        }

        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }

}
