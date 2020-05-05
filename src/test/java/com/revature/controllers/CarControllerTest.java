package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Car;
import com.revature.models.CarDTO;
import com.revature.services.CarService;
import com.revature.services.TripService;
import com.revature.utilities.MockObjects;
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
@WebMvcTest(CarController.class)
public class CarControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper om;
	
	@MockBean
	private CarService cs;
	
	@MockBean
	private TripService ts;
		
	@Test
	public void testGettingCars() throws Exception {
		
		List<Car> cars = new ArrayList<>();
		cars.add(new Car());
		cars.add(new Car());
		when(cs.getCars()).thenReturn(cars);

		mvc.perform(get("/cars"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)));
	}
	
	@Test
	public void testGettingCarById() throws Exception {

		Car car = MockObjects.getCar();
		when(cs.getCarById(1)).thenReturn(java.util.Optional.of(car));

		mvc.perform(get("/cars/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.carId").value(1));
	}
	
	@Test
	public void testGettingCarByUserId() throws Exception {

		Car car = MockObjects.getCar();
		when(cs.getCarByUserId(1)).thenReturn(car);

		mvc.perform(get("/cars/users/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.carId").value(1));
	}
	
	@Test
	public void testAddingCar() throws Exception {

		Car car = MockObjects.getCar();
		CarDTO dto = new CarDTO(car);
		when(cs.addCar(new Car(dto))).thenReturn(car);

		mvc.perform(post("/cars")
				.contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(car)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.color").value("red"));
	}
		
	@Test
	public void testUpdatingCar() throws Exception {
		Car car = MockObjects.getCar();
		CarDTO dto = new CarDTO(car);
		when(cs.updateCar(new Car(dto))).thenReturn(car);

		mvc.perform(put("/cars/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(car)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.color").value("red"))
				.andReturn();
	}
	
	@Test
	public void testDeletingCar() throws Exception {

		Car car = MockObjects.getCar();
		String returnedStr = "Car with id: " + car.getCarId() + " was deleted";
		when(cs.deleteCarById(1)).thenReturn(returnedStr);

		mvc.perform(delete("/cars/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value(returnedStr));
	}
}
