package com.userserviceservice.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class UserDto {

	private Integer userId;

	private String name;

	private Date dateOfBirth;

	private Double salary;

	private Integer age;


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

	public UserDto(Integer userId, String name, Date dateOfBirth, Double salary, Integer age) {
		super();
		this.userId = userId;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.salary = salary;
		this.age = age;
	}
	
	public UserDto() {
		
	}


}
