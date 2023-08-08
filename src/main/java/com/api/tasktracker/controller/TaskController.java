package com.api.tasktracker.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.tasktracker.entity.Task;
import com.api.tasktracker.service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskController {

	@Autowired
	private TaskService taskService;

	// @Validated insure that the input data is validated as per the annotations
	// used in entity

	@PostMapping("/tasks")
	public ResponseEntity<Object> createTask(@RequestBody @Validated Task task) {
		try {
			boolean isCreated = taskService.addTask(task);
			if (isCreated) {
				// Generate or retrieve the task ID after adding the task
	            String taskId = task.getId();

	            // Create a Map to hold the response values
	            Map<String, Object> response = new HashMap<>();
	            response.put("message", "Task Created Successfully.");
	            response.put("taskId", taskId);

	            // Return the Map as the response with 201 Created status
	            return new ResponseEntity<>(response, HttpStatus.CREATED);
			} else {

				// if not then it returns 400 Bad request
				return new ResponseEntity<>("Unable to create task. Please check your input.", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// if unexpected error occurs then 500 Internal Server Error
			return new ResponseEntity<>("An error occurred while processing the request.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// @Validated insure that the input data is validated as per the annotations
	// used in entity

	@PutMapping("/tasks/{id}")
	public ResponseEntity<Object> updateTask(@PathVariable String id, @RequestBody @Validated Task task) {
		try {

			boolean isUpdated = taskService.updateTask(id, task);
			if (isUpdated) {
				// if task is updated successfully it return 200 code OK
				return new ResponseEntity<>("Task Updated Successfully", HttpStatus.OK);
			} else {

				// if not then it returns 404 Not Found
				return new ResponseEntity<>("Task not found or unable to update. Please check your input",
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// if unexpected error occurs then 500 Internal Server Error
			return new ResponseEntity<>("An error occurred while processing the request",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/tasks/{id}")
	public ResponseEntity<Object> getById(@PathVariable String id) {
		try {
			Task task = taskService.getTaskById(id);
			if (task != null) {
				// if the task found return 200 OK with the task as an output
				return new ResponseEntity<>(task, HttpStatus.OK);
			} else {
				// if not then return task with ID not found, return 404 not found
				return new ResponseEntity<>("Task not found for ID: " + id, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// or as a corner case if unexpected error occurs then return 500 Internal
			// server error
			return new ResponseEntity<>("An error occurred while processing the request",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<Object> deleteTaskById(@PathVariable String id) {
		try {
			Task task = taskService.getTaskById(id);
			if (task != null) {
				if (taskService.deleteById(id)) {
					return new ResponseEntity<>("Task with ID " + id + " Deleted Successfully", HttpStatus.OK);
				} else {
					// If the deletion operation fails, return a response with an appropriate error
					// message.
					return new ResponseEntity<>("Unable to delete task with ID: " + id,
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				return new ResponseEntity<>("Task not found for ID: " + id, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("An error occurred while processing the request",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/tasks")
	public ResponseEntity<Object> getAllTasks() {
	    try {
	        List<Task> allTasks = taskService.getAllTasks();
	        if (!allTasks.isEmpty()) {
	            // If tasks are found, return 200 OK along with the tasks
	            return new ResponseEntity<>(allTasks, HttpStatus.OK);
	        } else {
	            // If no tasks are found, return a response with a message
	            
	            return new ResponseEntity<>("No Task Found", HttpStatus.NO_CONTENT);
	        }
	    } catch (Exception e) {
	        // If an unexpected error occurs, return a 500 Internal Server Error response
	        return new ResponseEntity<>("An error occurred while processing the request.", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

}
