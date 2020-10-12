package com.userdataservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.userdataservice.service.UserDataIF;
import com.userserviceservice.model.UserDto;

@RestController
public class UserDataController {

	@Autowired
	private UserDataIF service;
	
	@RequestMapping(value = "/getUserDetails/{userId}", method = RequestMethod.GET)
	public UserDto getUserDetails(@PathVariable String userId) {
		return service.readUser(userId);

	}
}
