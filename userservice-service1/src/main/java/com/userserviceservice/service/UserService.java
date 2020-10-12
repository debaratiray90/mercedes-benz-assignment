package com.userserviceservice.service;

import java.util.Base64;

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
	

	@Value("${aes-secret-key}")
	private String aesSecretKey;
	
	@Value("${aes-salt-key}")
	private String aesSaltKey;

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
		UserDto user =  restTemplate.getForObject(url, UserDto.class);
		return decryptUser(user);
		
	}

	private UserDto decryptUser(UserDto user) {
		UserDto userdto = new UserDto();
		userdto.setUserId(decrypt(user.getUserId()));
		userdto.setAge(decrypt(user.getAge()));
		userdto.setDateOfBirth(decrypt(user.getDateOfBirth()));
		userdto.setName(decrypt(user.getName()));
		userdto.setSalary(decrypt(user.getSalary()));
		return userdto;
	}
	
	private String decrypt(String encrpyValue) {
		return new String(com.userserviceservice.utils.AES.decrypt(Base64.getDecoder().decode(encrpyValue), aesSecretKey, aesSaltKey));
	}

	
}
