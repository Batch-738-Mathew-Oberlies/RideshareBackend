package com.revature.services.impl;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.models.Address;
import com.revature.models.AddressDTO;
import com.revature.repositories.AddressRepository;

@RunWith(SpringRunner.class)
public class AddressServiceImplTest {
	
	@InjectMocks
	AddressServiceImpl asi;
	
	@Mock
	AddressRepository ar;
	
	@Test
	public void testFindAll() {
		List<Address> expected = new ArrayList<>();
		expected.add(new Address());
		expected.add(new Address());
		when(ar.findAll()).thenReturn(expected);
		List<Address> actual = asi.findAll();
		
		verify(ar).findAll();
		assertEquals(2, actual.size());
	}
	
	@Test
	public void testFindAllDTO() {
		List<Address> expected = new ArrayList<>();
		expected.add(new Address());
		expected.add(new Address());
		when(ar.findAll()).thenReturn(expected);
		List<AddressDTO> actual = asi.findAllDTO();
		
		verify(ar).findAll();
		assertEquals(2, actual.size());
	}
	
	@Test
	public void testAddAddress() {
		Address expected = new Address();
		expected.setId(1);
		when(ar.save(expected)).thenReturn(expected);
		Address actual = asi.addAddress(expected);
		
		verify(ar).save(expected);
		assertEquals(1, actual.getId());
	}
	
	@Test
	public void testGetAddressById() {
		Address expected = new Address();
		expected.setId(1);
		when(ar.findById(1)).thenReturn(Optional.of(expected));
		Optional<Address> actual = asi.getAddressById(1);
		
		verify(ar).findById(1);
		if(actual.isPresent()) {
			assertEquals(expected, actual.get());
		} else {
			fail();
		}	
	}
	
	@Test
	public void testUpdateAddress() {
		Address expected = new Address();
		when(ar.save(expected)).thenReturn(expected);
		Address actual = asi.updateAddress(expected);
		
		verify(ar).save(expected);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testDeleteAddressById() {
		Address expected = new Address();
		expected.setId(1);
		doAnswer(invoke -> {
			Object arg = invoke.getArgument(0);
			assertEquals(1, arg);
			return null;
		}).when(ar).deleteById(anyInt());
		asi.deleteAddressById(1);
		
		verify(ar).deleteById(1);
	}
	
}
