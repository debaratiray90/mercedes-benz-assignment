
package com.userdataservice.service;

import com.userserviceservice.model.User;
import com.userserviceservice.model.UserDto;

public interface UserDataIF {

	void updateUser(User user);

	void saveUser(User user);

	UserDto readUser(String userId);
}
