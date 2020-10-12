package com.userserviceservice.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class User {

	private Integer userId;

	private String name;

	private Date dateOfBirth;

	private Double salary;

	private Integer age;

	
	private String fileType;
	
	private String eventType;
	

	@XmlTransient
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	@XmlElement
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@XmlElement
	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@XmlElement
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@XmlTransient
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/*
	 * public User(Double salary, Integer userId, String name, Integer age, Date
	 * dateOfBirth) { super(); this.salary = salary; this.userId = userId; this.name
	 * = name; this.age = age; this.dateOfBirth = dateOfBirth; }
	 */

	public User() {

	}

}
