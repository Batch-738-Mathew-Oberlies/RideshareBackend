package com.revature.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.models.Trip;
import com.revature.models.TripDTO;
import com.revature.models.TripStatus;
import com.revature.models.User;
import com.revature.repositories.TripRepository;

@RunWith(SpringRunner.class)
public class TripServiceImplTest {
	
	@InjectMocks
	TripServiceImpl tsi;
	
	@Mock
	TripRepository tr;
	
	@Test
	public void testGetTrips() {
		List<Trip> expected = new ArrayList<>();
		expected.add(new Trip());
		expected.add(new Trip());
		when(tr.findAll()).thenReturn(expected);
		List<Trip> actual = tsi.getTrips();
		
		verify(tr).findAll();
		assertEquals(2, actual.size());
	}
	
	@Test
	public void testGetTripsDTO() {
		List<Trip> expected = new ArrayList<>();
		List<User> riders = new ArrayList<>();
		riders.add(new User());
		riders.add(new User());
		Trip trip1 = new Trip();
		Trip trip2 = new Trip();
		trip1.setRiders(riders);
		trip2.setRiders(riders);
		expected.add(trip1);
		expected.add(trip2);
		when(tr.findAll()).thenReturn(expected);
		List<TripDTO> actual = tsi.getTripsDTO();
		
		verify(tr).findAll();
		assertEquals(2, actual.size());
	}
	
	@Test
	public void testGetTripById() {
		Trip expected = new Trip();
		expected.setTripId(1);
		when(tr.findById(1)).thenReturn(Optional.of(expected));
		Optional<Trip> actual = tsi.getTripById(1);
		if(actual.isPresent()) {
			assertEquals(expected, actual.get());
		} else {
			fail();
		}
		verify(tr).findById(1);
	}
	
	@Test
	public void testGetTripsByDriverId() {
		List<Trip> expected = new ArrayList<>();
		User driver = new User();
		driver.setUserId(1);
		Trip trip1 = new Trip();
		trip1.setDriver(driver);
		Trip trip2 = new Trip();
		trip2.setDriver(driver);
		expected.add(trip1);
		expected.add(trip2);
		when(tr.getTripsByDriverId(1)).thenReturn(expected);
		List<Trip> actual = tsi.getTripsByDriverId(1);
		User actualDriver = actual.get(0).getDriver();
		
		verify(tr).getTripsByDriverId(1);
		assertEquals(2, actual.size());
		assertEquals(1, actualDriver.getUserId());
			
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
