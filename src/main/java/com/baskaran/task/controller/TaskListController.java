package com.baskaran.task.controller;


import com.baskaran.task.dto.TaskListDto;
import com.baskaran.task.mappers.TaskListMapper;
import com.baskaran.task.mappers.TaskMapper;
import com.baskaran.task.model.TaskList;
import com.baskaran.task.service.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/task-lists")
public class TaskListController {

    @Autowired
    private TaskListService taskListService;

    @Autowired
    private TaskListMapper taskListMapper;

    @GetMapping
    public List<TaskListDto> listTaskList(){

    return taskListService.listTaskList()
            .stream()
            .map(taskListMapper::toDto).toList();
    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto){
        TaskList createTaskList= taskListService.createTaskList(taskListMapper.fromDto(taskListDto));

        return taskListMapper.toDto(createTaskList);
    }

    @GetMapping({"/{task_list_id}/tasks"})
    public Optional<TaskListDto> getTaskListById(@PathVariable("task_list_id")UUID taskListId){
        return taskListService.getTaskList(taskListId).map(taskListMapper::toDto);
    }

    @PutMapping({"/{task_list_id}"})
    public TaskListDto updateTaskList(@PathVariable("task_list_id")UUID taskListId,@RequestBody TaskListDto taskListDto){
       TaskList updatedtaskList=taskListService.updateTaskList(taskListId, taskListMapper.fromDto(taskListDto));
       return taskListMapper.toDto(updatedtaskList);
    }

    @DeleteMapping({"/{task_list_id}"})
    public void deleteList(@PathVariable("task_list_id") UUID taskListId){
        taskListService.deleteTaskList(taskListId);
    }
}
