package com.userserviceservice.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {

	@NotNull(message = "Salary is mandatory")
	private Integer userId;

	@NotBlank(message = "Name is mandatory")
	private String name;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Past
	private Date dateOfBirth;

	@NotNull(message = "Salary is mandatory")
	private Double salary;

	@NotNull(message = "Age is mandatory")
	private Integer age;

	private String fileType;
	
	private String eventType;
	

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public User(Double salary, Integer userId, String name, Integer age, Date dateOfBirth) {
		super();
		this.salary = salary;
		this.userId = userId;
		this.name = name;
		this.age = age;
		this.dateOfBirth = dateOfBirth;
	}

	public User() {

	}

}
