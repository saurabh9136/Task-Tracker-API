package com.api.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.tasktracker.entity.Task;

/*
 * using a JPA repository which allows to use predefined methods 
 * so we can't write a manual queries to interact with database 
 */
public interface TaskRepository extends JpaRepository<Task, String> {

}
