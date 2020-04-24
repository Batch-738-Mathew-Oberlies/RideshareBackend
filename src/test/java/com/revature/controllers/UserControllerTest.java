package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Address;
import com.revature.models.Batch;
import com.revature.models.User;
import com.revature.services.BatchService;
import com.revature.services.DistanceService;
import com.revature.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper om;

	@MockBean
	private UserService us;

	@MockBean
	private BatchService batchService;

	@MockBean
	private DistanceService distanceService;

	@Test
	public void testGettingUsers() throws Exception {

		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		when(us.getUsers()).thenReturn(users);

		mvc.perform(get("/users"))
		   .andExpect(status().isOk())
		   .andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	public void testGettingUserById() throws Exception {

		User user = new User(1, "userName", new Batch(), "adonis", "cabreja", "adonis@gmail.com", "123-456-7890");
		user.setDriver(true);
		user.setActive(true);
		user.setAcceptingRides(true);
		when(us.getUserById(1)).thenReturn(java.util.Optional.of(user));

		mvc.perform(get("/users/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.userId").value(1));
	}
	
	@Test
	public void testGettingUserByUsername() throws Exception {

		List<User> users = new ArrayList<>();
		users.add(new User(1, "userName", new Batch(), "adonis", "cabreja", "adonis@gmail.com", "123-456-7890"));
		when(us.getUserByUsername("userName")).thenReturn(users);

		mvc.perform(get("/users?username=userName"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].userName").value("userName"));
	}
	
	@Test
	public void testGettingUserByRole() throws Exception {

		List<User> users = new ArrayList<>();
		User user = new User(1, "userName", new Batch(), "adonis", "cabreja", "adonis@gmail.com", "123-456-7890");
		user.setDriver(true);
		user.setActive(true);
		user.setAcceptingRides(true);
		users.add(user);
		when(us.getUserByRole(true)).thenReturn(users);

		mvc.perform(get("/users?is-driver=true"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].driver").value("true"));
	}
	
	@Test
	public void testGettingUserByRoleAndLocation() throws Exception {

		List<User> users = new ArrayList<>();
		User user = new User(1, "userName", new Batch(), "adonis", "cabreja", "adonis@gmail.com", "123-456-7890");
		user.setDriver(true);
		user.setActive(true);
		user.setAcceptingRides(true);
		users.add(user);
		when(us.getUserByRoleAndLocation(true, "location")).thenReturn(users);
		mvc.perform(get("/users?is-driver=true&location=location"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].driver").value("true"));
	}
	
	@Test
	public void testAddingUser() throws Exception {

		Batch batch = new Batch(111, "address");
		User user = new User(1, "userName", batch, "adonis", "cabreja", "adonis@gmail.com", "123-456-7890");
		Address h = new Address(0, "123 Fake Street", "Youngsville", "MI", "12123");
		user.setHAddress(h);
		user.setWAddress(h);
		user.setDriver(true);
		user.setActive(true);
		user.setAcceptingRides(true);

		when(us.addUser(user)).thenReturn(user);

		mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(user)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.userName").value("userName"));
	}
	
	@Test
	public void testUpdatingUser() throws Exception {
		Batch batch = new Batch(111, "address");
		User user = new User(1, "userName", batch, "adonis", "cabreja", "adonis@gmail.com", "123-456-7890");
		Address h = new Address(0, "123 Fake Street", "Youngsville", "MI", "12123");
		user.setHAddress(h);
		user.setWAddress(h);
		when(us.updateUser(user)).thenReturn(user);

		mvc.perform(put("/users/{id}", 1).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(user)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.userName").value("userName"));
	}
	
	@Test
	public void testDeletingUser() throws Exception {
		User user = new User(1, "userName", new Batch(), "adonis", "cabreja", "adonis@gmail.com", "123-456-7890");
		String returnedStr = "User with id: " + user.getUserId() + " was deleted.";
		when(us.deleteUserById(1)).thenReturn(returnedStr);

		mvc.perform(delete("/users/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value(returnedStr));
	}

}
