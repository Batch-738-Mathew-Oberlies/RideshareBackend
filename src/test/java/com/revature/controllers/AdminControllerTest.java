package com.revature.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Admin;
import com.revature.services.AdminService;
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
@WebMvcTest(AdminController.class)
public class AdminControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper om;
	
	@MockBean
	private AdminService as;
	
	@Test
	public void testGettingAdmins() throws Exception {
		
		List<Admin> admins = new ArrayList<>();
		admins.add(new Admin());
		admins.add(new Admin());
		when(as.getAdmins()).thenReturn(admins);

		mvc.perform(get("/admins"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)));
	}
	
	@Test
	public void testGettingAdminById() throws Exception {

		Admin admin = new Admin(1, "UserName");
		when(as.getAdminById(1)).thenReturn(java.util.Optional.of(admin));

		mvc.perform(get("/admins/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.adminId").value(1));
	}
	
	@Test
	public void testCreatingAdmin() throws Exception {

		Admin admin = new Admin(1, "userName");
		when(as.createAdmin(new Admin(1, "userName"))).thenReturn(admin);

		mvc.perform(post("/admins")
				.contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(admin)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$").value(admin));
	}
	
	@Test
	public void testUpdatingAdmin() throws Exception {

		Admin admin = new Admin(1, "userName");
		when(as.updateAdmin(new Admin(1, "userName"))).thenReturn(admin);

		mvc.perform(put("/admins/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(admin)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value(admin));
	}
	
	@Test
	public void testDeletingAdmin() throws Exception {
		
		Admin admin = new Admin(1, "userName");
		String returnedStr = "Admin with id " + admin.getAdminId() + " was deleted";
		when(as.deleteAdminById(1)).thenReturn(returnedStr);

		mvc.perform(delete("/admins/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value(returnedStr));
	}
}
