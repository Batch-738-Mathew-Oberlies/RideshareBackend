package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Batch;
import com.revature.services.BatchService;
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
@WebMvcTest(BatchController.class)
public class BatchControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper om;
	
	@MockBean
	private BatchService bs;
	
	@Test
	public void testGettingBatches() throws Exception {
		
		List<Batch> batches = new ArrayList<>();
		batches.add(new Batch());
		batches.add(new Batch());
		when(bs.getBatches()).thenReturn(batches);

		mvc.perform(get("/batches"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)));
	}
	
	@Test
	public void testGettingBatchByNumber() throws Exception {

		Batch batch = new Batch(123, "location");
		when(bs.getBatchByNumber(123)).thenReturn(java.util.Optional.of(batch));

		mvc.perform(get("/batches/{number}", 123))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.batchNumber").value(123));
	}
	
	@Test
	public void testGettingBatchesByLocation() throws Exception {
		
		List<Batch> batches = new ArrayList<>();
		batches.add(new Batch(123, "location"));
		batches.add(new Batch(456, "location"));
		when(bs.getBatchByLocation("location")).thenReturn(batches);

		mvc.perform(get("/batches?location=location"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)));
	}
	
	@Test
	public void testAddingBatch() throws Exception {

		Batch batch = new Batch(123, "location");
		when(bs.addBatch(new Batch(123, "location"))).thenReturn(batch);

		mvc.perform(post("/batches")
				.contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(batch)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$").value(batch));
	}
	
	@Test
	public void testUpdatingBatch() throws Exception {

		Batch batch = new Batch(123, "location");
		when(bs.updateBatch(new Batch(123, "location"))).thenReturn(batch);

		mvc.perform(put("/batches/{id}", 123)
				.contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(batch)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value(batch));
	}
	
	@Test
	public void testDeletingBatch() throws Exception {
		
		Batch batch = new Batch(123, "location");
		String returnedStr = "Batch number: " + batch.getBatchNumber() + " was deleted.";
		when(bs.deleteBatchByNumber(123)).thenReturn(returnedStr);

		mvc.perform(delete("/batches/{id}", 123))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value(returnedStr));
	}
}
