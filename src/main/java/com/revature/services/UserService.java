package com.revature.services;

import com.revature.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

	List<User> getUsers();

	Optional<User> getUserById(int id);

	List<User> getUserByUsername(String username);

	List<User> getUserByRole(boolean isDriver);

	List<User> getUserByRoleAndLocation(boolean isDriver, String location);

	User addUser(User user);

	User updateUser(User user);

	String deleteUserById(int id);

	List<User> getActiveDrivers();
}
