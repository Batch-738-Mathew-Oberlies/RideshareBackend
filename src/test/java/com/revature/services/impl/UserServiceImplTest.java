package com.revature.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.beans.Batch;
import com.revature.beans.User;
import com.revature.repositories.UserRepository;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl usi;
	
	@Mock
	private UserRepository ur;
	
	@Test
	public void testGettingUsers() {
		
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		when(ur.findAll()).thenReturn(users);
		
		assertEquals(2, usi.getUsers().size());
	}
//	
//	@Test
//	public void testGettingUserById() {
//		
//		User expected = new User(1, "userName", new Batch(), "adonis", "cabreja", "adonis@gmail.com", "123-456-789");
//		when(ur.getOne(1)).thenReturn(expected);
//		User actual = usi.getUserById(1);
//		
//		assertEquals(expected, actual);
//	}
	
	@Test
	public void testGettingUserByUsername() {
		
		List<User> expected = new ArrayList<>();
		expected.add(new User(1, "userName", new Batch(), "adonis", "cabreja", "adonis@gmail.com", "123-456-789"));
		when(ur.getUserByUsername("userName")).thenReturn(expected);
		List<User> actual = usi.getUserByUsername("userName");
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGettingUserByRole() {
		
		List<User> expected = new ArrayList<>();
		expected.add(new User(1, "userName", new Batch(), "adonis", "cabreja", "adonis@gmail.com", "123-456-789"));
		when(ur.getUserByRole(true)).thenReturn(expected);
		List<User> actual = usi.getUserByRole(true);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGettingUserByRoleAndLocation() {
		
		List<User> expected = new ArrayList<>();
		expected.add(new User(1, "userName", new Batch(), "adonis", "cabreja", "adonis@gmail.com", "123-456-789"));
		when(ur.getUserByRoleAndLocation(true, "location")).thenReturn(expected);
		List<User> actual = usi.getUserByRoleAndLocation(true, "location");
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testAddingUser() {
		
		User expected = new User(1, "userName", new Batch(), "adonis", "cabreja", "adonis@gmail.com", "123-456-789");
		when(ur.save(expected)).thenReturn(expected);
		User actual = usi.addUser(expected);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testUpdatingUser() {
		
		User expected = new User(1, "userName", new Batch(), "adonis", "cabreja", "adonis@gmail.com", "123-456-789");
		when(ur.save(expected)).thenReturn(expected);
		User actual = usi.updateUser(expected);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testDeletingUser() {
		
		String expected = "User with id: 1 was deleted.";
		when(ur.existsById(1)).thenReturn(true);
		String actual = usi.deleteUserById(1);
		
		assertEquals(expected, actual);
	}
	
}
