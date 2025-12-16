package com.baskaran.task.mappers.impl;

import com.baskaran.task.dto.TaskDto;
import com.baskaran.task.dto.TaskListDto;
import com.baskaran.task.mappers.TaskListMapper;
import com.baskaran.task.mappers.TaskMapper;
import com.baskaran.task.model.Task;
import com.baskaran.task.model.TaskList;
import com.baskaran.task.model.TaskStatus;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper{

    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        TaskList taskList=new TaskList();

        taskList.setId(taskListDto.id());
        taskList.setTitle(taskListDto.title());
        taskList.setDescription(taskListDto.description());
        Optional.ofNullable(taskListDto.tasks())
                        .map(tasks->tasks.stream()
                                .map(taskMapper::fromDto)
                                .toList()
                        ).orElse(null);
        taskList.setCreateAt(LocalDateTime.now());
        taskList.setUpdateAt(LocalDateTime.now());
        return taskList;
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {

        if(taskList==null){
            return null;
        }

        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::toDto).toList())
                        .orElse(List.of()) // Provide an empty list instead of throwing an error
        );
    }

    private Double calculateTaskListProgress(List<Task> tasks){
        if(tasks==null){
            return null;
        }

        long closeTaskCount = tasks.stream().filter(task -> TaskStatus.CLOSE==task.getStatus()).count();
        return (double) closeTaskCount/tasks.size();
    }
}
