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
	private TripServiceImpl tsi;
	
	@Mock
	private TripRepository tr;
	
	@Test
	public void testGettingTrips() {
		List<Trip> trips = new ArrayList<>();
		trips.add(new Trip());
		trips.add(new Trip());
		when(tr.findAll()).thenReturn(trips);
		
		assertEquals(2, tsi.getTrips().size());
	}
	
	@Test
	public void testGettingTripById() {
		List<User> riders = new ArrayList<>();
		riders.add(MockObjects.getAdonis());
		riders.add(MockObjects.getAdonis());
		Trip expected = MockObjects.getTrip();
		when(tr.findById(1)).thenReturn(Optional.of(expected));
		Optional<Trip> actual = tsi.getTripById(1);
		if (actual.isPresent()) {
			assertEquals(expected, actual.get());
		} else {
			fail();
		}
	}
	
	@Test
	public void testGettingTripsByDriverId() {
		List<User> riders = new ArrayList<>();
		riders.add(MockObjects.getAdonis());
		riders.add(MockObjects.getAdonis());
		List<Trip> trips = new ArrayList<>();
		trips.add(MockObjects.getTrip());
		trips.add(MockObjects.getTrip());
		when(tr.getTripsByDriverId(1)).thenReturn(trips);
		
		assertEquals(2, tsi.getTripsByDriverId(1).size());
	}
	
	@Test
	public void testGettingTripsByRiderId() {
		List<User> riders = new ArrayList<>();
		riders.add(MockObjects.getAdonis());
		riders.add(MockObjects.getAdonis());
		List<Trip> trips = new ArrayList<>();
		trips.add(MockObjects.getTrip());
		trips.add(MockObjects.getTrip());
		when(tr.getTripsByRiderId(1)).thenReturn(trips);
		
		assertEquals(2, tsi.getTripsByRiderId(1).size());
	}
	
	@Test
	public void testAddingTrips() {
		List<User> riders = new ArrayList<>();
		riders.add(MockObjects.getAdonis());
		riders.add(MockObjects.getAdonis());
		Trip expected = MockObjects.getTrip();
		when(tr.save(expected)).thenReturn(expected);
		Trip actual = tsi.addTrip(expected);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testUpdatingUser() {
		List<User> riders = new ArrayList<>();
		riders.add(MockObjects.getAdonis());
		riders.add(MockObjects.getAdonis());
		Trip expected = MockObjects.getTrip();
		when(tr.save(expected)).thenReturn(expected);
		Trip actual = tsi.updateTrip(expected);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testDeletingTripById() {
		String expected = "Trip with id: 1 was deleted.";
		when(tr.existsById(1)).thenReturn(true);
		String actual = tsi.deleteTripById(1);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetCurrentTripsByDriverId() {
		List<Trip> list = new ArrayList<>();
		Trip trip1 = MockObjects.getTrip();
		Trip trip2 = MockObjects.getTrip();
		User driver = MockObjects.getAdonis();
		trip1.setDriver(driver);
		trip2.setDriver(driver);
		list.add(trip1);
		list.add(trip2);
		when(tr.getMostRecentTripsByDriverIdAndTripStatus(1, TripStatus.CURRENT))
			.thenReturn(list);
		when(tr.getMostRecentTripsByDriverIdAndTripStatus(2, TripStatus.CURRENT))
		.thenReturn(Collections.emptyList());
		Trip actual1 = tsi.getCurrentTripByDriverId(1);
		Trip actual2 = tsi.getCurrentTripByDriverId(2);
		
		verify(tr).getMostRecentTripsByDriverIdAndTripStatus(1, TripStatus.CURRENT);
		verify(tr).getMostRecentTripsByDriverIdAndTripStatus(2, TripStatus.CURRENT);
		assertEquals(list.get(0), actual1);
		assertNull(actual2);	
	}
	
	@Test
	public void testGetTripsByDriverIdDTO() {
		List<Trip> list = new ArrayList<>();
		User driver = MockObjects.getAdonis();
		Trip trip1 = MockObjects.getTrip();
		trip1.setDriver(driver);
		Trip trip2 = MockObjects.getTrip();
		trip2.setDriver(driver);
		list.add(trip1);
		list.add(trip2);
		when(tr.getMostRecentTripsByDriverIdAndTripStatus(1, TripStatus.CURRENT))
			.thenReturn(list);
	}
}
