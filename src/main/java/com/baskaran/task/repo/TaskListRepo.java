package com.baskaran.task.repo;

import com.baskaran.task.model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskListRepo extends JpaRepository <TaskList, UUID>{

}
