package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.*;
import com.revature.services.TripService;
import com.revature.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TripController.class)
public class TripControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TripService tripService;

    @MockBean
    private UserService userService;

    @Test
    public void testGettingTrips() throws Exception {
        List<TripDTO> trips = new ArrayList<>();
        trips.add(new TripDTO(getTrip()));
        trips.add(new TripDTO(getTrip()));

        when(tripService.getTripsDTO()).thenReturn(trips);

        mockMvc.perform(get("/trips"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGettingTripById() throws Exception {
        Trip trip = getTrip();
        when(tripService.getTripById(1)).thenReturn(java.util.Optional.of(trip));

        mockMvc.perform(get("/trips/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tripId").value(1));
    }

    private Trip getTrip() {
        Trip trip = new Trip();
        trip.setTripId(1);
        trip.setName("Grocery store");
        trip.setDriver(getAdonis());
        trip.setAvailableSeats(1);
        trip.setDeparture(getAdonis().getHAddress());
        trip.setDestination(getAdonis().getWAddress());
        trip.setTripDate(LocalDateTime.now());
        trip.setTripStatus(TripStatus.CURRENT);
        return trip;
    }

    private User getAdonis() {
        User adonis = new User();
        adonis.setUserId(1);
        adonis.setUserName("userName");
        Batch batch = new Batch();
        batch.setBatchLocation("Reston");
        batch.setBatchNumber(15);
        adonis.setBatch(batch);
        adonis.setFirstName("Adonis");
        adonis.setLastName("Cabreja");
        adonis.setEmail("adonis@gmail.com");
        adonis.setPhoneNumber("123-456-7890");
        adonis.setDriver(true);
        adonis.setActive(true);
        adonis.setAcceptingRides(true);
        Address address = new Address();
        address.setCity("Youngsville");
        address.setState("MI");
        address.setStreet("123 Fake Street");
        address.setZip("12123");
        address.setId(5);
        adonis.setHAddress(address);
        adonis.setWAddress(address);
        return adonis;
    }
}
