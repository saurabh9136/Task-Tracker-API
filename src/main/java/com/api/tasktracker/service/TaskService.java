package com.api.tasktracker.service;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;

import com.api.tasktracker.entity.Task;

@ComponentScan
public interface TaskService {

	public Task getTaskById(String id);

	public List<Task> getAllTasks();

	public boolean addTask(Task task);

	public boolean updateTask(String id, Task task);

	public boolean deleteById(String id);
}
