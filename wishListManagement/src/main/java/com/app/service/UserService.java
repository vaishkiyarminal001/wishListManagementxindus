package com.app.service;

import java.util.List;

import com.app.entity.User;

public interface UserService {
	
	 List<User> getUsers();
	 User createUser(User user);
	 void deleteUser(Long id);

}
