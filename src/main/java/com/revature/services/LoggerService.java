package com.revature.services;

import org.apache.logging.log4j.Logger;

public interface LoggerService {
	Logger getAccess();
	Logger getBusiness();
	Logger getException();
	Logger getPerformance();
}