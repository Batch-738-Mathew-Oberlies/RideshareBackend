package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Trip;
import com.revature.models.TripDTO;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.services.DistanceService;
import com.revature.services.TripService;
import com.revature.services.UserService;
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
import java.util.Optional;

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

    @MockBean
    private DistanceService distanceService;

    @Test
    public void testGetTrips() throws Exception {
        List<TripDTO> trips = new ArrayList<>();
        trips.add(new TripDTO(MockObjects.getTrip()));
        trips.add(new TripDTO(MockObjects.getTrip()));

        when(tripService.getTripsDTO()).thenReturn(trips);

        mockMvc.perform(get("/trips"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGetTripsFail() throws Exception {
        List<TripDTO> trips = new ArrayList<>();

        when(tripService.getTripsDTO()).thenReturn(trips);

        mockMvc.perform(get("/trips"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetTripById() throws Exception {
        Trip trip = MockObjects.getTrip();

        when(tripService.getTripById(1)).thenReturn(Optional.of(trip));

        mockMvc.perform(get("/trips/{id}", trip.getTripId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tripId").value(1));
    }

    @Test
    public void testGetTripByIdFail() throws Exception {
        Trip trip = MockObjects.getTrip();

        when(tripService.getTripById(2)).thenReturn(Optional.of(trip));

        mockMvc.perform(get("/trips/{id}", trip.getTripId()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testAddTrip() throws Exception {
        Trip trip = MockObjects.getTrip();
        TripDTO tripDTO = new TripDTO(trip);

        when(tripService.addTrip(new Trip(tripDTO))).thenReturn(trip);

        mockMvc.perform(post("/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(trip)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Grocery store"));
    }

    @Test
    public void testUpdateTrip() throws Exception {
        Trip trip = MockObjects.getTrip();
        TripDTO tripDTO = new TripDTO(trip);

        when(tripService.getTripById(trip.getTripId())).thenReturn(Optional.of(trip));
        when(tripService.updateTrip(new Trip(tripDTO))).thenReturn(trip);

        mockMvc.perform(put("/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(trip)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Grocery store"))
                .andReturn();
    }

    @Test
    public void testUpdateTripFail() throws Exception {
        Trip trip = MockObjects.getTrip();

        mockMvc.perform(put("/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(trip)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteTripById() throws Exception {
        Trip trip = MockObjects.getTrip();
        String success = "Trip with id: " + trip.getTripId() + " was deleted";

        when(tripService.getTripById(trip.getTripId())).thenReturn(Optional.of(trip));
        when(tripService.deleteTripById(1)).thenReturn(success);

        mockMvc.perform(delete("/trips/{id}", trip.getTripId()))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$").value(success));
    }

    @Test
    public void testDeleteTripByIdFail() throws Exception {
        Trip trip = MockObjects.getTrip();

        mockMvc.perform(delete("/trips/{id}", trip.getTripId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetTripsByRiderId() throws Exception {
        List<TripDTO> trips = new ArrayList<>();
        TripDTO trip1 = new TripDTO(MockObjects.getTrip());
        TripDTO trip2 = new TripDTO(MockObjects.getTrip());

        List<UserDTO> riders = new ArrayList<>();
        UserDTO rider = new UserDTO(MockObjects.getAdonis());
        riders.add(rider);

        trip1.setRiders(riders);
        trip2.setRiders(riders);

        trips.add(trip1);
        trips.add(trip2);

        when(tripService.getTripsByRiderIdDTO(rider.getUserId())).thenReturn(trips);

        mockMvc.perform(get(String.format(
                "%s%s%s",
                "/trips/rider",
                "?riderId=",
                rider.getUserId()
        )))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGetTripsByRiderIdFail() throws Exception {
        UserDTO rider = new UserDTO(MockObjects.getAdonis());
        mockMvc.perform(get(String.format(
                "%s%s%s",
                "/trips/rider",
                "?riderId=",
                rider.getUserId()
        )))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdateTripRiders() throws Exception {
        Trip trip = MockObjects.getTrip();
        trip.setRiders(new ArrayList<>());
        User rider = MockObjects.getAdonis();

        when(tripService.getTripById(trip.getTripId())).thenReturn(Optional.of(trip));
        when(userService.getUserById(rider.getUserId())).thenReturn(Optional.of(rider));

        when(tripService.updateTrip(trip)).thenReturn(trip);

        mockMvc.perform(post(String.format(
                "%s%s%s%s",
                "/trips/rider?tripId=",
                trip.getTripId(),
                "&riderId=",
                rider.getUserId()
        )))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.riders", hasSize(1)));
    }

    @Test
    public void testUpdateTripRidersFail() throws Exception {
        Trip trip = MockObjects.getTrip();
        User rider = MockObjects.getAdonis();

        mockMvc.perform(post(String.format(
                "%s%s%s%s",
                "/trips/rider?tripId=",
                trip.getTripId(),
                "&riderId=",
                rider.getUserId()
        )))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteTripRider() throws Exception {
        Trip trip = MockObjects.getTrip();
        User rider = MockObjects.getAdonis();
        List<User> riders = new ArrayList<>();
        riders.add(rider);
        trip.setRiders(riders);

        when(tripService.getTripById(trip.getTripId())).thenReturn(Optional.of(trip));
        when(userService.getUserById(rider.getUserId())).thenReturn(Optional.of(rider));

        when(tripService.updateTrip(trip)).thenReturn(trip);

        mockMvc.perform(delete(String.format(
                "%s%s%s%s",
                "/trips/rider?tripId=",
                trip.getTripId(),
                "&riderId=",
                rider.getUserId()
        )))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.riders", hasSize(0)));
    }

    @Test
    public void testDeleteTripRiderFailToRemove() throws Exception {
        Trip trip = MockObjects.getTrip();
        User rider1 = MockObjects.getAdonis();
        rider1.setUserId(1);
        User rider2 = MockObjects.getAdonis();
        rider2.setUserId(2);
        List<User> riders = new ArrayList<>();
        riders.add(rider1);
        trip.setRiders(riders);

        when(tripService.getTripById(trip.getTripId())).thenReturn(Optional.of(trip));
        when(userService.getUserById(rider2.getUserId())).thenReturn(Optional.of(rider2));

        when(tripService.updateTrip(trip)).thenReturn(trip);

        mockMvc.perform(delete(String.format(
                "%s%s%s%s",
                "/trips/rider?tripId=",
                trip.getTripId(),
                "&riderId=",
                rider2.getUserId()
        )))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void testDeleteTripRiderFailNotFound() throws Exception {
        Trip trip = MockObjects.getTrip();
        User rider = MockObjects.getAdonis();

        mockMvc.perform(delete(String.format(
                "%s%s%s%s",
                "/trips/rider?tripId=",
                trip.getTripId(),
                "&riderId=",
                rider.getUserId()
        )))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetTripsByDriverId() throws Exception {
        List<TripDTO> trips = new ArrayList<>();
        TripDTO trip1 = new TripDTO(MockObjects.getTrip());
        TripDTO trip2 = new TripDTO(MockObjects.getTrip());

        trips.add(trip1);
        trips.add(trip2);

        when(tripService.getTripsByDriverIdDTO(trip1.getDriver().getUserId())).thenReturn(trips);

        mockMvc.perform(get(String.format(
                "%s%s%s",
                "/trips/driver",
                "?driverId=",
                trip1.getDriver().getUserId()
        )))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGetTripsByDriverIdFail() throws Exception {
        UserDTO driver = new UserDTO(MockObjects.getAdonis());
        UserDTO rider = new UserDTO(MockObjects.getAdonis());

        mockMvc.perform(get(String.format(
                "%s%s%s%s%s",
                "/trips/driver",
                "?driverId=",
                driver.getUserId(),
                "&riderId=",
                rider.getUserId()
        )))
                .andExpect(status().isNoContent());
    }
}
