package com.revature.services.impl;

import com.revature.models.Trip;
import com.revature.models.TripStatus;
import com.revature.models.User;
import com.revature.repositories.TripRepository;
import com.revature.utilities.MockObjects;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TripServiceImplTest {
	@InjectMocks
	private TripServiceImpl tripService;
	
	@Mock
	private TripRepository tripRepository;
	
	@Test
	public void testGetTrips() {
		List<Trip> trips = new ArrayList<>();
		trips.add(MockObjects.getTrip());
		trips.add(MockObjects.getTrip());

		when(tripRepository.findAll()).thenReturn(trips);
		
		assertEquals(2, tripService.getTripsDTO().size());
	}
	
	@Test
	public void testGetTripById() {
		Trip expected = MockObjects.getTrip();

		when(tripRepository.findById(expected.getTripId())).thenReturn(Optional.of(expected));

		Optional<Trip> actual = tripService.getTripById(expected.getTripId());
		if (actual.isPresent()) assertEquals(expected, actual.get());
		else fail();
	}

	@Test
	public void testGetTripsByRiderId() {
		List<User> riders = new ArrayList<>();
		User rider = MockObjects.getAdonis();
		riders.add(rider);

		Trip trip1 = MockObjects.getTrip();
		trip1.setRiders(riders);
		Trip trip2 = MockObjects.getTrip();
		trip2.setRiders(riders);

		List<Trip> trips = new ArrayList<>();
		trips.add(trip1);
		trips.add(trip2);

		when(tripRepository.getTripsByRiderId(rider.getUserId())).thenReturn(trips);
		
		assertEquals(2, tripService.getTripsByRiderIdDTO(rider.getUserId()).size());
	}

	@Test
	public void testGetTripsByDriverId() {
		User driver = MockObjects.getAdonis();

		List<Trip> trips = new ArrayList<>();
		Trip trip1 = MockObjects.getTrip();
		trip1.setDriver(driver);
		trips.add(trip1);

		Trip trip2 = MockObjects.getTrip();
		trip2.setDriver(driver);
		trips.add(trip2);

		when(tripRepository.getTripsByDriverId(driver.getUserId())).thenReturn(trips);

		assertEquals(2, tripService.getTripsByDriverIdDTO(driver.getUserId()).size());
	}

	@Test
	public void testGetTripsByDriverIdAndRiderId() {
		User driver = MockObjects.getAdonis();
		driver.setUserId(1);

		List<User> riders = new ArrayList<>();
		User rider = MockObjects.getAdonis();
		rider.setUserId(2);
		riders.add(rider);

		List<Trip> trips = new ArrayList<>();
		Trip trip1 = MockObjects.getTrip();
		trip1.setDriver(driver);
		trip1.setRiders(riders);
		trips.add(trip1);

		Trip trip2 = MockObjects.getTrip();
		trip2.setDriver(driver);
		trip2.setRiders(riders);
		trips.add(trip2);

		when(tripRepository.getTripsByDriverIdAndByRiderId(
				driver.getUserId(), rider.getUserId()
		))
				.thenReturn(trips);

		assertEquals(2, tripService.getTripsByDriverIdAndByRiderIdDTO(
				driver.getUserId(), rider.getUserId()
		)
				.size());
	}

	@Test
	public void testAddTrips() {
		Trip expected = MockObjects.getTrip();

		when(tripRepository.save(expected)).thenReturn(expected);

		Trip actual = tripService.addTrip(expected);
		assertEquals(expected, actual);
	}

	@Test
	public void testUpdateTrip() {
		Trip expected = MockObjects.getTrip();

		when(tripRepository.save(expected)).thenReturn(expected);

		Trip actual = tripService.updateTrip(expected);
		assertEquals(expected, actual);
	}

	@Test
	public void testDeleteTripById() {
		String expected = "Trip with id: 1 was deleted.";

		when(tripRepository.existsById(1)).thenReturn(true);

		String actual = tripService.deleteTripById(1);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetCurrentTrips() {
		List<Trip> trips = new ArrayList<>();
		Trip trip1 = MockObjects.getTrip();
		Trip trip2 = MockObjects.getTrip();
		trips.add(trip1);
		trips.add(trip2);

		when(tripRepository.getByTripStatus(TripStatus.CURRENT)).thenReturn(trips);

		List<Trip> actual = tripService.getCurrentTrips();
		assertNotNull(actual);
	}

	@Test
	public void testGetCurrentTripsByDriverId() {
		List<Trip> trips = new ArrayList<>();
		Trip trip1 = MockObjects.getTrip();
		Trip trip2 = MockObjects.getTrip();
		User driver = MockObjects.getAdonis();
		trip1.setDriver(driver);
		trip2.setDriver(driver);
		trips.add(trip1);
		trips.add(trip2);

		when(tripRepository.getMostRecentTripsByDriverIdAndTripStatus(1, TripStatus.CURRENT))
				.thenReturn(trips);
		when(tripRepository.getMostRecentTripsByDriverIdAndTripStatus(2, TripStatus.CURRENT))
				.thenReturn(Collections.emptyList());

		Trip actual1 = tripService.getCurrentTripByDriverId(1);
		Trip actual2 = tripService.getCurrentTripByDriverId(2);

		verify(tripRepository).getMostRecentTripsByDriverIdAndTripStatus(1, TripStatus.CURRENT);
		verify(tripRepository).getMostRecentTripsByDriverIdAndTripStatus(2, TripStatus.CURRENT);

		assertEquals(trips.get(0), actual1);
		assertNull(actual2);
	}
}
