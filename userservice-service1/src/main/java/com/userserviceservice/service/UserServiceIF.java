package com.userserviceservice.service;

import com.userserviceservice.model.User;
import com.userserviceservice.model.UserDto;

public interface UserServiceIF {

	 void updateUser(User user, String fileType);
	 
	 void saveUser(User user,String fileType);
	 
	 UserDto readUser(String userId);
}
