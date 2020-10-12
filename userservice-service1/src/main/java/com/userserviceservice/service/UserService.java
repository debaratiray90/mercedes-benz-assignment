package com.userserviceservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userserviceservice.model.User;
import com.userserviceservice.model.UserDto;

@Service
public class UserService implements UserServiceIF {

	@Value("${user-dataservice-url}")
	private String userDataServiceUrl;

	@Autowired
	private KafkaProducer producer;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void updateUser(User user, String fileType) {
		user.setFileType(fileType);
		user.setEventType("Update");
		producer.sendMessage(user);
	}

	@Override
	public void saveUser(User user, String fileType) {
		user.setFileType(fileType);
		user.setEventType("Add");
		producer.sendMessage(user);

	}

	@Override
	public UserDto readUser(String userId) {
		String url = new StringBuilder(userDataServiceUrl).append(userId).toString();
		return restTemplate.getForObject(url, UserDto.class);
	}

}
