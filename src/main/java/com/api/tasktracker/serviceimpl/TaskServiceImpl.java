package com.api.tasktracker.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.tasktracker.entity.Task;
import com.api.tasktracker.repository.TaskRepository;
import com.api.tasktracker.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	// using slf4j to use logger for service Impl because all the business logic was
	// writing inside it
	private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

	@Override
	public Task getTaskById(String id) {

		try {
			logger.info("User requested to fetch task by ID{}:", id); // adding event in log
			// Attempt to retrieve a Task from the repository
			Optional<Task> optionalTask = taskRepository.findById(id);
			if (optionalTask.isPresent()) { // if present
				return optionalTask.get();
			} else {
				logger.warn("Task With ID{} not found", id); // adding data as warning if id is not present
				return null;
			}
		} catch (Exception e) {
			logger.error("Error fetching task with ID{} : {}", id, e.getMessage(), e); // adding error
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Task> getAllTasks() {

		try {

			List<Task> allTasks = taskRepository.findAll();

			logger.info("User requested to fetch all Tasks");

			return allTasks;

		} catch (Exception e) {
			logger.error("Error fetching all tasks: {}", e.getMessage(), e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean addTask(Task task) {
		try {
			logger.info("User Requested to create a new Task");
			// saved the task using the repository
			taskRepository.save(task);

			// log the task creation
			logger.info("Task Created Successfully - Title: {}, Description: {}, Due Date{}", task.getTitle(),
					task.getDescription(), task.getDueDate());

			return true;

		} catch (Exception e) {
			// if error occur
			logger.error("Error creating Task:{}", e.getMessage(), e);
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateTask(String id, Task task) {
		try {
			logger.info("User requested to update the existing task Id:{}", id);
			// Check if the task with the given id exists or not
			Optional<Task> optionalTask = taskRepository.findById(id);
			if (optionalTask.isPresent()) {
				// Get the existing task from the database
				Task existingTask = optionalTask.get();

				// Update the properties of the existing task with the new values
				existingTask.setTitle(task.getTitle());
				existingTask.setDescription(task.getDescription());
				existingTask.setDueDate(task.getDueDate());

				// Save the updated task to the database
				taskRepository.save(existingTask);

				logger.info("Updating task with id {} with new values - Title: {}, Description: {}, Due Date: {}", id,
						task.getTitle(), task.getDescription(), task.getDueDate());

				return true;
			}
		} catch (Exception e) {
			// if error occur
			logger.error("Error updating task with id{} :{}", id, e.getMessage(), e);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteById(String id) {
		try {
			logger.info("User reequested to delete a task with Id:{}", id);
			// Check if the task with the given id exists in the database
			Optional<Task> optionalTask = taskRepository.findById(id);
			if (optionalTask.isPresent()) {
				// If the task exists, delete it from the database
				taskRepository.deleteById(id);
				logger.info("Task with id{} has been deleted Successfully", id);
				return true;
			} else {
				logger.warn("Task With ID{} not found", id);
			}
		} catch (Exception e) {
			logger.error("Error Deleting Task with ID {}: {}", id, e.getMessage(), e);
			e.printStackTrace();
		}
		return false;
	}

}
