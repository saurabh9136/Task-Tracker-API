package com.api.tasktracker.entity;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // setter & getter
@AllArgsConstructor // constructor with args
@NoArgsConstructor // constructor with no args

@Entity // annotate class as table
@Table(name = "tasks") // use to set the name of table
public class Task {

	/*
	 * for validation use @Notblank & @FutureOrPresent & NotNull for database
	 * annotations @Id & @Column
	 */

	/*
	 * as per the problem statement we have to make it identity but if the id is
	 * string then we cannot make it IDENTITY because it returns int value that's
	 * why using a Generated and generic generator to generate a unique id for a
	 * string
	 */

	@Id // primary key
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id") // column are used to set name of column inside the table & other properties
	private String id;

	@NotBlank(message = "Title should not be blank") // of field is blank then it throw the message
	@Column(name = "title")
	private String title;

	@NotBlank(message = "Description should not be blank")
	@Column(name = "description")
	private String description;

	@FutureOrPresent(message = "Due Date should be in the future or present") // this annotation are used to check
																				// whether date is not in past
	@NotNull(message = "Please enter a Due date as it's Mandatory")
	@Column(name = "due_date")
	private Date dueDate;

}
