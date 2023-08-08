package com.api.tasktracker.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.tasktracker.entity.Task;
import com.api.tasktracker.repository.TaskRepository;

@SpringBootTest
class TaskServiceImplTest {

	@Mock // used to create dummy data to simulate database interactions
	private TaskRepository repository;

	@InjectMocks // Injecting the mock repository into the service for testing
	private TaskServiceImpl taskService;

	@Test
	void testGetTaskById() {
		// Mock the taskRepository.findById() method
		Task mockTask = new Task();
		mockTask.setId("01");
		mockTask.setTitle("Title 1");
		mockTask.setDescription("Desc 1");
		mockTask.setDueDate(new Date());

		when(repository.findById("01")).thenReturn(Optional.of(mockTask));

		// call the method
		Task resultTask = taskService.getTaskById("01");

		// verify the result
		assertNotNull(resultTask);
		assertEquals("01", resultTask.getId());
		assertEquals("Title 1", resultTask.getTitle());
		assertEquals("Desc 1", resultTask.getDescription());
	}

	@Test
	void testGetAllTasks() {
		// Mock the taskRepository.findAll() method
		List<Task> mockTasks = new ArrayList<>();

		// Add some mock tasks to the list
		when(repository.findAll()).thenReturn(mockTasks);

		List<Task> resultTasks = taskService.getAllTasks();

		// Verify the result
		assertNotNull(resultTasks);
		assertEquals(mockTasks.size(), resultTasks.size()); // logic - if both of the tasks size is same then logically
															// its same
		verify(repository).findAll();
	}

	@Test
	void testAddTask() {
		// Mock the taskRepository.save() method
		Task taskToAdd = new Task();
		taskToAdd.setTitle("New Task");
		taskToAdd.setDescription("New Task Description");
		taskToAdd.setDueDate(new Date());

		when(repository.save(any(Task.class))).thenReturn(taskToAdd);

		boolean isAdded = taskService.addTask(taskToAdd);

		// Verify the result
		assertTrue(isAdded);
	}

	@Test
	void testUpdateTask() {
		// Mock the taskRepository.findById() methods
		Task existingTask = new Task();
		existingTask.setId("01");
		existingTask.setTitle("Title 1");
		existingTask.setDescription("Desc 1");
		existingTask.setDueDate(new Date());

		Task updatedTask = new Task();
		updatedTask.setId("01");
		updatedTask.setTitle("Updated Title");
		updatedTask.setDescription("Updated Description");
		updatedTask.setDueDate(new Date());

		when(repository.findById("01")).thenReturn(Optional.of(existingTask));
		when(repository.save(any(Task.class))).thenReturn(updatedTask);

		boolean isUpdated = taskService.updateTask("01", updatedTask);

		// Verify the result
		assertTrue(isUpdated); // updateTask method returns true upon successful update
		verify(repository).findById("01");
		verify(repository).save(updatedTask); // save method was called with the updated task
	}

	@Test
	void testDeleteById() {
		// Mock the taskRepository.deleteById() method
		Task mockTask = new Task();
		mockTask.setId("01");
		mockTask.setDescription("Task 1");
		mockTask.setTitle("Title 1");
		mockTask.setDueDate(new Date());

		when(repository.findById("01")).thenReturn(Optional.of(mockTask));

		boolean isDeleted = taskService.deleteById("01");

		// verify the result
		assertTrue(isDeleted);
		verify(repository).findById("01");
		verify(repository).deleteById("01");
	}

}
