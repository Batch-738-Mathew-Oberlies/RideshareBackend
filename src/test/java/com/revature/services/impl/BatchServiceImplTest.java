package com.revature.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BatchServiceImplTest {
	
	@InjectMocks
	private BatchServiceImpl bsi;
	
	@Mock
	private BatchRepository br;
	
	@Test
	public void testGettingBatches() {
		
		List<Batch> batches = new ArrayList<>();
		batches.add(new Batch());
		batches.add(new Batch());
		when(br.findAll()).thenReturn(batches);
		
		assertEquals(2, bsi.getBatches().size());
	}

	@Test
	public void testGettingBatchByNumber() {

		Batch expected = new Batch(123, "location");
		when(br.findById(123)).thenReturn(Optional.of(expected));
		Optional<Batch> actual = bsi.getBatchByNumber(123);
		if (actual.isPresent())
			assertEquals(expected, actual.get());
		else
			fail();
	}

	@Test
	public void testGettingBatchesByLocation() {
		
		List<Batch> batches = new ArrayList<>();
		batches.add(new Batch(123, "location"));
		batches.add(new Batch(456, "location"));
		when(br.getBatchByLocation("location")).thenReturn(batches);
		
		assertEquals(2, bsi.getBatchByLocation("location").size());
	}
	
	@Test
	public void testAddingBatch() {
		
		Batch expected = new Batch(123, "location");
		when(br.save(expected)).thenReturn(expected);
		Batch actual = bsi.addBatch(expected);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testUpdatingBatch() {
		
		Batch expected = new Batch(123, "location");
		when(br.save(expected)).thenReturn(expected);
		Batch actual = bsi.updateBatch(expected);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testDeletingBatch() {
		
		String expected = "Batch number: 123 was deleted.";
		when(br.existsById(123)).thenReturn(true);
		String actual = bsi.deleteBatchByNumber(123);
		
		assertEquals(expected, actual);
	}
}
