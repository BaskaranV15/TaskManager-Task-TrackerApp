package com.baskaran.task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "task_list")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id",nullable = false,updatable = false)
    private UUID id;
    @Column(name="title",nullable = false)
    private String title;
    private String description;
    @OneToMany(mappedBy = "taskList",cascade = {CascadeType.REMOVE,CascadeType.PERSIST}) // one task list have many tasks
    private List<Task> tasks;
    @Column(name="create",nullable = false)
    private LocalDateTime createAt;
    @Column(name="update",nullable = false)
    private LocalDateTime updateAt;

}
