package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.DistanceService;
import com.revature.services.UserService;
import com.revature.utilities.MockObjects;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DistanceService distanceService;

	@MockBean
	private UserService userService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testLoginPass() throws Exception {
		User u = MockObjects.getAdonis();
		List<User> adonisList = new ArrayList<>();
		adonisList.add(u);
		when(userService.getUserByUsername("userName")).thenReturn(adonisList);

		mockMvc.perform(get("/login?userName=userName&passWord=passWord"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(String.format("%s %s", u.getFirstName(), u.getLastName())));
	}

	@Test
	public void testLoginFail() throws Exception {
		List<User> userList = new ArrayList<>();
		when(userService.getUserByUsername("userName")).thenReturn(userList);

		mockMvc.perform(get("/login?userName=userName&passWord=passWord"))
				.andExpect(jsonPath("$.userNotFound").value("User not found!"));
	}

	@Test
	public void testApiKey() throws Exception {
		String s = "testApiKey";
		when(distanceService.getGoogleMAPKey()).thenReturn(s);

		mockMvc.perform(get("/login/getGoogleApi"))
				.andExpect(jsonPath("$.googleMapAPIKey").value(s));
	}
}
