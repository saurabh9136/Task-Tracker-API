# Task Tracker API

Welcome to the Task Tracker API project! This project demonstrates a robust and production-quality RESTful API for a task tracking application using Java, Spring Boot, and PostgreSQL as the backend store. This README provides detailed instructions on how to set up the database, build the project, run tests, and start the API.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Database Setup](#database-setup)
- [Building the Project](#building-the-project)
- [Running Tests](#running-tests)
- [Running the API](#running-the-api)

## Prerequisites

Before you begin, make sure you have the following installed:

- Java Development Kit (JDK) 8 or later
- Apache Maven
- PostgreSQL database

## Database Setup

1. Create a PostgreSQL database named `taskdb` with the following configuration:

   - URL: `jdbc:postgresql://localhost:5432/taskdb`
   - Username: `postgres`
   - Password: `root` // you can change as per your password

2. Create a table named `tasks` with the following columns:
  // if you are using hibernate JPA then you dont have to make this manually
   - `id` (UUID): Primary key, generated automatically
   - `title` (VARCHAR): Title of the task
   - `description` (VARCHAR): Description of the task
   - `due_date` (TIMESTAMP): Due date of the task

## Building the Project

1. Clone this repository to your local machine:
   
   git clone https://github.com/your-username/task-tracker-api.git

2. Navigate to the project directory:

   cd task-tracker-api

3. Build the project using Maven:

   mvn clean install -DskipTests

## Running Tests

1. To run unit tests and integration tests:

   mvn test or else run as Junit Test


## Running the API

1. Start the API using Maven:

   mvn spring-boot:run or else run as Spring Boot app

2. The API will be accessible at `http://localhost:2411/api/tasks`.

## API Endpoints

- **POST /api/tasks**: Create a new task.
- **GET /api/tasks/{id}**: Retrieve a task by its ID.
- **GET /api/tasks**: Retrieve a list of all tasks.
- **PUT /api/tasks/{id}**: Update an existing task by its ID.
- **DELETE /api/tasks/{id}**: Delete a task by its ID.

### Create a Task

**Request:**

- Method: `POST`
- URL: `http://localhost:2411/api/tasks`
- Body:
json
  {
    "title": "Task Title",
    "description": "Task Description",
    "dueDate": "2023-08-15"
  }

**Response:**

- Success:
  - Status: `201 Created`
  - Body: `"Task Created Successfully."
  -         taskId: "task id"`

- Error:
  - Status: `400 Bad Request`
  - Body: `"Unable to create task. Please check your input."`

### Retrieve a Task

**Request:**

- Method: `GET`
- URL: `http://localhost:2411/api/tasks/{id}`

**Response:**

- Success:
  - Status: `200 OK`
  - Body: Task details in JSON format

- Error:
  - Status: `404 Not Found`
  - Body: `"Task not found for ID: {id}"`

### Retrieve All Tasks

**Request:**

- Method: `GET`
- URL: `http://localhost:2411/api/tasks`

**Response:**

- Success:
  - Status: `200 OK`
  - Body: List of tasks in JSON format

- Error:
  - Status: `204 No Content`
  - Body: `"No tasks found."`

### Update a Task

**Request:**

- Method: `PUT`
- URL: `http://localhost:2411/api/tasks/{id}`
- Body: Updated task details in JSON format

**Response:**

- Success:
  - Status: `200 OK`
  - Body: `"Task Updated Successfully."`

- Error:
  - Status: `404 Not Found`
  - Body: `"Task not found or unable to update. Please check your input."`

### Delete a Task

**Request:**

- Method: `DELETE`
- URL: `http://localhost:2411/api/tasks/{id}`

**Response:**

- Success:
  - Status: `200 OK`
  - Body: `"Task with ID {id} Deleted Successfully"`

- Error:
  - Status: `404 Not Found`
  - Body: `"Task not found for ID: {id}"`

Please replace `{id}` with the actual ID of the task you're working with.

## Conclusion

Congratulations! You've set up the Task Tracker API project successfully. Feel free to explore and customize the codebase to fit your needs. If you encounter any issues or have questions, please refer to the documentation or reach out for assistance.

Happy coding!
