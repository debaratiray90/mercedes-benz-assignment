package com.userserviceservice.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userserviceservice.model.User;
import com.userserviceservice.model.UserDto;
import com.userserviceservice.service.UserServiceIF;

@RestController
public class UserController {

	@Autowired
	private UserServiceIF userService;

	@RequestMapping(value = "/readUser/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Object> readUser(@PathVariable(required = true) String userId) {
		UserDto userDto = userService.readUser(userId);
		if (null != userDto) {
			return ResponseEntity.status(HttpStatus.OK).body(userDto);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("No user found for userId : " + userId);
		}

	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
	public void updateUser(@Valid @RequestBody User user, @RequestParam(required = true) String fileType,
			BindingResult result) {
		userService.updateUser(user, fileType.toUpperCase());

	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ResponseEntity<String> saveUser(@RequestBody @Valid User user, @RequestParam(required = true) String fileType,
			BindingResult result) {
		userService.saveUser(user, fileType.toUpperCase());
		return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully!");

	}
}
