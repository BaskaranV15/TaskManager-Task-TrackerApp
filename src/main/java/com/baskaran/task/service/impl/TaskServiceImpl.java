package com.baskaran.task.service.impl;

import com.baskaran.task.model.Task;
import com.baskaran.task.model.TaskList;
import com.baskaran.task.model.TaskPriority;
import com.baskaran.task.model.TaskStatus;
import com.baskaran.task.repo.TaskListRepo;
import com.baskaran.task.repo.TaskRepo;
import com.baskaran.task.service.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private TaskListRepo taskListRepo;

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepo.findByTaskListId(taskListId);

    }



    @Override
    public Task creatTask(UUID taskListId, Task task) {
        if(task.getId()!=null){
            throw new IllegalArgumentException("Task already has an Id");
        }
        if(task.getTitle()==null || task.getDescription().isBlank()){
            throw new IllegalArgumentException("Task must have a title");
        }

         TaskPriority taskPriority=Optional.ofNullable(task.getPriority())
                .orElse(TaskPriority.MEDIUM);
        TaskStatus taskStatus=TaskStatus.OPEN;

        TaskList taskList= taskListRepo.findById(taskListId)
                .orElseThrow(()->new IllegalArgumentException("Invalid Task List Id provided"));

        LocalDateTime now=LocalDateTime.now();
        Task taskToSave=new Task(null,
                task.getTitle(),
                task.getDescription(),
                taskList,
                task.getDueDate(),
                taskStatus,
                taskPriority,
                now,
                now
                );
        return taskRepo.save(taskToSave);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepo.findByIdAndTaskList_Id(taskId, taskListId);
    }


    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {

        Task existingTask = taskRepo.findByIdAndTaskList_Id(taskId, taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setUpdateAt(LocalDateTime.now());

        return taskRepo.save(existingTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {

        taskRepo.deleteByIdAndTaskList_Id(taskId,taskListId);


    }
}
