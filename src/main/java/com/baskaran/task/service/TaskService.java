package com.baskaran.task.service;

import com.baskaran.task.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {

    List<Task> listTasks(UUID taskListId);
    Task creatTask(UUID taskListId,Task task);
    Optional<Task> getTask(UUID taskListId,UUID taskId);
}
