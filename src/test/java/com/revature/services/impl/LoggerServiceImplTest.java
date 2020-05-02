package com.revature.services.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.apache.logging.log4j.Logger;

public class LoggerServiceImplTest {

	
    LoggerServiceImpl service = new LoggerServiceImpl();
	
	@Test
	public void testGetAccess() {
		Logger log = service.getAccess();
		assertTrue(log instanceof Logger);
		Logger log2 = service.getAccess();
		assertTrue(log2 instanceof Logger);
		assertTrue(log == log2);
	}
	
	@Test
	public void testGetBusiness() {
		Logger log = service.getBusiness();
		assertTrue(log instanceof Logger);
		Logger log2 = service.getBusiness();
		assertTrue(log2 instanceof Logger);
		assertTrue(log == log2);
	}
	
	@Test
	public void testGetException() {
		Logger log = service.getException();
		assertTrue(log instanceof Logger);
		Logger log2 = service.getException();
		assertTrue(log2 instanceof Logger);
		assertTrue(log == log2);
	}
	
	@Test
	public void testGetPerformance() {
		Logger log = service.getPerformance();
		assertTrue(log instanceof Logger);
		Logger log2 = service.getPerformance();
		assertTrue(log2 instanceof Logger);
		assertTrue(log == log2);
	}

}
