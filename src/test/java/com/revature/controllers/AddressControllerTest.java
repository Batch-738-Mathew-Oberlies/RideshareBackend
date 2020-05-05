package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Address;
import com.revature.models.AddressDTO;
import com.revature.services.AddressService;
import com.revature.utilities.MockObjects;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AddressController.class)
public class AddressControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private AddressService addressService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void getAllAddress() throws Exception {
		List<AddressDTO> addresses = new ArrayList<>();
		Address a = MockObjects.getAddress();
		a.setId(1);
		for (int i = 0; i < 5; i++)
			addresses.add(new AddressDTO(a));

		when(addressService.findAllDTO()).thenReturn(addresses);

		mvc.perform(get("/address/"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
	public void getAllAddressNone() throws Exception {
		List<AddressDTO> address = new ArrayList<>();
		when(addressService.findAllDTO()).thenReturn(address);
		mvc.perform(get("/address"))
				.andExpect(status().isNoContent());
	}

	@Test
	public void testAddValid() throws Exception {
		Address a = MockObjects.getAddress();
		AddressDTO dto = new AddressDTO(a);
		when(addressService.addAddress(a)).thenReturn(a);

		mvc.perform(post("/address")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(a)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value(dto));
	}

	@Test
	public void testAddConflict() throws Exception {
		Address a = MockObjects.getAddress();
		a.setId(1);
		when(addressService.getAddressById(1)).thenReturn(java.util.Optional.of(a));

		mvc.perform(post("/address")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(a)))
			.andExpect(status().isConflict());
	}

	@Test
	public void testAddBad() throws Exception {
		Address a = MockObjects.getAddress();
		when(addressService.addAddress(a)).thenReturn(null);

		mvc.perform(post("/address/")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(a)))
			.andExpect(status().isBadRequest());
	}

}
