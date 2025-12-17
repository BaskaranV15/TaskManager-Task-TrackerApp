package com.baskaran.task.repo;

import com.baskaran.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepo extends JpaRepository<Task, UUID> {

    List<Task> findByTaskListId(UUID id);
    Optional<Task> findById(UUID id);
    Optional<Task> findByIdAndTaskList_Id(UUID taskId, UUID taskListId);

}
