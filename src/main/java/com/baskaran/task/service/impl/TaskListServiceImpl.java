package com.baskaran.task.service.impl;

import com.baskaran.task.model.TaskList;
import com.baskaran.task.repo.TaskListRepo;
import com.baskaran.task.service.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@Service
public class TaskListServiceImpl implements TaskListService{

    @Autowired
    private TaskListRepo taskListRepo;


    @Override
    public List<TaskList> listTaskList() {
        return taskListRepo.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {

        if(taskList.getId()!=null){
            throw new IllegalArgumentException("Task List already has an Id");
        }
        if(taskList.getTitle()==null || taskList.getTitle().isBlank()){
            throw new IllegalArgumentException("Task List must contain a title");
        }
        return taskListRepo.save(new TaskList(null,taskList.getTitle(),taskList.getDescription(),null, LocalDateTime.now(),LocalDateTime.now()));
    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepo.findById(id);
    }

    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if(taskList.getId()==null){
            throw new IllegalArgumentException("task list must have an Id");
        }

        if(!Objects.equals(taskList.getId(),taskListId)){
            throw new IllegalArgumentException("Attempt to change a task list but it not permitted");
        }

        TaskList existingTaskList= taskListRepo.findById(taskListId).orElseThrow(()->new IllegalArgumentException("Task List not Found"));

        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdateAt(LocalDateTime.now());
        return taskListRepo.save(existingTaskList);
    }

    @Override
    public void deleteTaskList(UUID id) {
        taskListRepo.deleteById(id);
    }
}
