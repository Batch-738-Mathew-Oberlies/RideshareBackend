package com.revature.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.base.Optional;
import com.revature.models.Address;
import com.revature.models.Batch;
import com.revature.models.Trip;
import com.revature.models.User;
import com.revature.repositories.TripRepository;

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
		Trip expected = new Trip(1, "Get Groceries", new User(), new List<User>(), 4, new Address(), new Address(), new LocalDateTime());
		when(tr.findById(1)).thenReturn(Optional.of(expected));
		Optional<Trip> actual = tsi.getTripById(1);
		if (actual.isPresent()) {
			assertEquals(expected, actual.get());
		} else {
			fail();
		}
	}
	
	@Test
	public List<Trip> testGettingTripsByDriverId() {
		List<Trip> trips = new ArrayList<>();
		User driver = new User(1, "userName", new Batch(), "jose", "avalos", "ja@ja.com", "123-456-7890");
		trips.add(new Trip(1, "Get Groceries", driver, new List<User>(), 4, new Address(), new Address(), new LocalDateTime());
		trips.add(new Trip(2, "Go to the gym", driver, new List<User>(), 4, new Address(), new Address(), new LocalDateTime());
		when(tr.getTripsByDriverId(1)).thenReturn(trips);
		
		assertEquals(2, tsi.getTripsByDriverId(1).size());
	}
	
	@Test
	public List<Trip> testGettingTripsByRiderId() {
		List<Trip> trips = new ArrayList<>();
		User rider = new User(1, "userName", new Batch(), "jose", "avalos", "ja@ja.com", "123-456-7890");
		trips.add(new Trip(1, "Get Groceries", new User(), rider, 4, new Address(), new Address(), new LocalDateTime());
		trips.add(new Trip(2, "Go to the gym", new User(), rider, 4, new Address(), new Address(), new LocalDateTime());
		when(tr.getTripsByRiderId(1)).thenReturn(trips);
		
		assertEquals(2, tsi.getTripsByRiderId(1).size());
	}
	
	@Test
	public Trip testAddingTrips() {
		Trip expected = new Trip(1, "Get Groceries", new User(), new List<User>(), 4, new Address(), new Address(), new LocalDateTime());
		when(tr.save(expected)).thenReturn(expected);
		Trip actual = tsi.addTrip(expected);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testUpdatingUser() {
		Trip expected = new Trip(1, "Get Groceries", new User(), new List<User>(), 4, new Address(), new Address(), new LocalDateTime());
		when(tr.save(expected)).thenReturn(expected);
		Trip actual = tsi.updateTrip(expected);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testDeletingUser() {
		String expected = "User with id: 1 was deleted.";
		when(tr.existsById(1)).thenReturn(true);
		String actual = tsi.deleteTripById(1);
		
		assertEquals(expected, actual);
	}
}
