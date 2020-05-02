package com.revature.services.impl;

import com.revature.models.*;
import com.revature.repositories.TripRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
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
		User driver = new User(1, "driver", new Batch(), "jose", "avalos", "ja@ja.com", "123-456-7890");
		List<User> riders = new ArrayList<>();
		riders.add(new User(2, "rider1", new Batch(), "jean", "lucas", "jl@jl.com", "123-456-7891"));
		riders.add(new User(3, "rider2", new Batch(), "galo", "romero", "gr@gr.com", "123-456-7892"));
		Address home = new Address(1, "123 Happy St", "#1", "Revature", "CA", "12345");
		Address work = new Address(1, "456 Work Rd", "#1", "Revature", "CA", "12345");
		Trip expected = new Trip(1, "Get Groceries", driver, riders, 4, home, work, LocalDateTime.now());
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
		User driver = new User(1, "driver", new Batch(), "jose", "avalos", "ja@ja.com", "123-456-7890");
		List<User> riders = new ArrayList<>();
		riders.add(new User(2, "rider1", new Batch(), "jean", "lucas", "jl@jl.com", "123-456-7891"));
		riders.add(new User(3, "rider2", new Batch(), "galo", "romero", "gr@gr.com", "123-456-7892"));
		Address home = new Address(1, "123 Happy St", "#1", "Revature", "CA", "12345");
		Address work = new Address(1, "456 Work Rd", "#1", "Revature", "CA", "12345");
		List<Trip> trips = new ArrayList<>();
		trips.add(new Trip(1, "Get Groceries", driver, riders, 4, home, work, LocalDateTime.now()));
		trips.add(new Trip(2, "Go to the gym", driver, riders, 4, home, work, LocalDateTime.now()));
		when(tr.getTripsByDriverId(1)).thenReturn(trips);
		
		assertEquals(2, tsi.getTripsByDriverId(1).size());
	}
	
	@Test
	public void testGettingTripsByRiderId() {
		User driver = new User(1, "driver", new Batch(), "jose", "avalos", "ja@ja.com", "123-456-7890");
		List<User> riders = new ArrayList<>();
		riders.add(new User(2, "rider1", new Batch(), "jean", "lucas", "jl@jl.com", "123-456-7891"));
		riders.add(new User(3, "rider2", new Batch(), "galo", "romero", "gr@gr.com", "123-456-7892"));
		Address home = new Address(1, "123 Happy St", "", "Revature", "CA", "12345");
		Address work = new Address(1, "456 Work Rd", "", "Revature", "CA", "12345");
		List<Trip> trips = new ArrayList<>();
		trips.add(new Trip(1, "Get Groceries", driver, riders, 4, home, work, LocalDateTime.now()));
		trips.add(new Trip(2, "Go to the gym", driver, riders, 4, home, work, LocalDateTime.now()));
		when(tr.getTripsByRiderId(1)).thenReturn(trips);
		
		assertEquals(2, tsi.getTripsByRiderId(1).size());
	}
	
	@Test
	public void testAddingTrips() {
		User driver = new User(1, "driver", new Batch(), "jose", "avalos", "ja@ja.com", "123-456-7890");
		List<User> riders = new ArrayList<>();
		riders.add(new User(2, "rider1", new Batch(), "jean", "lucas", "jl@jl.com", "123-456-7891"));
		riders.add(new User(3, "rider2", new Batch(), "galo", "romero", "gr@gr.com", "123-456-7892"));
		Trip expected = new Trip(1, "Get Groceries", driver, riders, 4, new Address(), new Address(), LocalDateTime.now());
		when(tr.save(expected)).thenReturn(expected);
		Trip actual = tsi.addTrip(expected);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testUpdatingUser() {
		User driver = new User(1, "driver", new Batch(), "jose", "avalos", "ja@ja.com", "123-456-7890");
		List<User> riders = new ArrayList<>();
		riders.add(new User(2, "rider1", new Batch(), "jean", "lucas", "jl@jl.com", "123-456-7891"));
		riders.add(new User(3, "rider2", new Batch(), "galo", "romero", "gr@gr.com", "123-456-7892"));
		Trip expected = new Trip(1, "Get Groceries", driver, riders, 4, new Address(), new Address(), LocalDateTime.now());
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
		User driver1 = new User();
		driver1.setUserId(1);
		Trip trip1 = new Trip();
		trip1.setDriver(driver1);
		Trip trip2 = new Trip();
		trip2.setDriver(driver1);
		list.add(trip1);
		list.add(trip2);
		when(tr.getMostRecentTripsByDriverIdAndTripStatus(1, TripStatus.CURRENT))
			.thenReturn(list);
		when(tr.getMostRecentTripsByDriverIdAndTripStatus(2, TripStatus.CURRENT))
		.thenReturn(Collections.emptyList());
		Trip actual = tsi.getCurrentTripByDriverId(1);
		Trip actual2 = tsi.getCurrentTripByDriverId(2);
		
		verify(tr).getMostRecentTripsByDriverIdAndTripStatus(1, TripStatus.CURRENT);
		verify(tr).getMostRecentTripsByDriverIdAndTripStatus(2, TripStatus.CURRENT);
		assertEquals(list.get(0), actual);
		assertNull(actual2);	
	}
	
	@Test
	public void testGetTripsByDriverIdDTO() {
		List<Trip> list = new ArrayList<>();
		User driver1 = new User();
		driver1.setUserId(1);
		Trip trip1 = new Trip();
		trip1.setDriver(driver1);
		Trip trip2 = new Trip();
		trip2.setDriver(driver1);
		list.add(trip1);
		list.add(trip2);
		when(tr.getMostRecentTripsByDriverIdAndTripStatus(1, TripStatus.CURRENT))
			.thenReturn(list);
		
	}
}
