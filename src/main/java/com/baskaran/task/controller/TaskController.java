package com.baskaran.task.controller;

import com.baskaran.task.dto.TaskDto;
import com.baskaran.task.mappers.TaskMapper;
import com.baskaran.task.model.Task;
import com.baskaran.task.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/task-lists/{task_list_id}/tasks")
public class TaskController {

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TaskServiceImpl taskService;

    @GetMapping()
    public List<TaskDto> ListTask(@PathVariable("task_list_id")UUID taskListId){

        return taskService.listTasks(taskListId)
                .stream()
                .map(taskMapper::toDto).toList();

    }

    @PostMapping
    public TaskDto creatTask(@PathVariable("task_list_id")UUID taskListId,@RequestBody TaskDto taskDto){
        Task taskCreate=  taskService.creatTask(taskListId,taskMapper.fromDto(taskDto));

        return taskMapper.toDto(taskCreate);
    }
    //api/task-lists/873dff85-13d9-4568-80e6-c8f4c0755104/tasks/24feaa92-537d-45fa-956f-0646c0b7d726

    @GetMapping("/{task_id}")
    public Optional<TaskDto> getTask(@PathVariable("task_list_id") UUID taskListId,
                                     @PathVariable("task_id")UUID taskId){

        return taskService.getTask(taskListId,taskId).map(taskMapper::toDto);
    }

    @PutMapping("/{task_id}")
    public TaskDto updateTask(@PathVariable("task_id")UUID taskID,
                              @PathVariable("task_list_id") UUID taskListId,
                              @RequestBody TaskDto taskDto){

        Task taskUpdate=taskService.updateTask(taskListId,taskID,taskMapper.fromDto(taskDto));

        return taskMapper.toDto(taskUpdate);
    }

    @DeleteMapping("/{task_id}")
    public void deleteTask(@PathVariable("task_id")UUID taskID,
                           @PathVariable("task_list_id") UUID taskListId){
        taskService.deleteTask(taskListId,taskID);
    }
}
