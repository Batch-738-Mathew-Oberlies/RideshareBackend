package com.revature.services;

import org.apache.logging.log4j.Logger;

public interface LoggerService {
	/**
	 * The access logger tracks attempts to access content beyond the permission 
	 * of the user. This logger is primarily used to keep records of possible 
	 * hacking attempts<br/>
	 * <br/> 
	 * Examples of some possibilities:<br/>
	 * <b>Trace</b> - wrong password on login<br/>
	 * <b>Debug</b> - bad request<br/>
	 * <b>Info</b><br/>
	 * <b>Warn</b> - request information not allowed for user<br/>
	 * <b>Error</b><br/>
	 * <b>Fatal</b> - positive confirmation of hacking attempt<br/>
	 * @return Logger
	 */
	Logger getAccess();
	/**
	 * The business logger tracks important business events such as creating accounts,
	 * joining vehicles, etc.<br/>
	 * <br/>
	 * Examples of some possibilities:<br/>
	 * <b>Trace</b> - almost any normal business event<br/>
	 * @return Logger
	 */
	Logger getBusiness();
	/**
	 * The exception logger tracks thrown events.<br/>
	 * <br/>
	 * Examples of some possibilities:<br/>
	 * <b>Trace</b> - exceptions that are designed to be caught as part of regular use<br/>
	 * <b>Debug</b> - exceptions meant for preliminary debugging.<br/>
	 * <b>Info</b><br/>
	 * <b>Warn</b> - expected exceptions that will cause loss of some functionality<br/>
	 * <b>Error</b> - errors and exceptions that will cause the thread to terminate<br/>
	 * <b>Fatal</b> - errors and exceptions that will cause the program to crash<br/>
	 * @return Logger
	 */
	Logger getException();
	/**
	 * The performance logger tracks the speed methods complete in.<br/>
	 * <br/>
	 * Examples of some possibilities:<br/>
	 * <b>Trace</b> - method completed in a normal amount of time<br/>
	 * <b>Debug</b> - method took unusually long to complete<br/>
	 * <b>Info</b><br/>
	 * <b>Warn</b> - method took unacceptably long to complete<br/>
	 * <b>Error</b><br/>
	 * <b>Fatal</b> - quit timer hit that prevented method from completing.<br/>
	 * @return Logger
	 */
	Logger getPerformance();
}