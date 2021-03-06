package com.revature.services.impl;

import com.revature.models.Batch;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

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

	@Test
	public void testGettingUserById() {

		User expected = new User(1, "userName", new Batch(), "adonis", "cabreja", "adonis@gmail.com", "123-456-789");
		when(ur.findById(1)).thenReturn(Optional.of(expected));
		Optional<User> actual = usi.getUserById(1);
		if (actual.isPresent())
			assertEquals(expected, actual.get());
		else
			fail();
	}

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
