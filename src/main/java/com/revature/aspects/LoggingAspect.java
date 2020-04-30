package com.revature.aspects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.revature.services.LoggerService;


@Component
@Aspect
public class LoggingAspect {
	private LoggerService loggerService;
	private HttpServletRequest request;
	private HttpServletResponse response;
	@Autowired
	public void setLoggerService(LoggerService loggerService) {
		this.loggerService = loggerService;
	}
	@Autowired(required = false)
	public void setRequest(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	/**
	 * Automatic logging of:
	 * 1) requests made including where the request came from, method, uri, and time
	 * 2) request bodies except for sensitive locations (Login)
	 * 3) exceptions thrown by a controller
	 */
	@SuppressWarnings("rawtypes")
	@Around("within(com.revature.controllers..*)")
	public Object logControllers(ProceedingJoinPoint jp) throws Throwable {
		if(request == null)return jp.proceed();
		Object result = null;
		boolean sensitive = false;
		String payload = "";
		if(jp.getTarget().toString().matches(".*Login.*"))sensitive = true;
		String parameters = buildRequestParameterSetring(); 
		//Log all requests
		String requestMessage = String.format("IP: %s made a %s request with parameters %s to %s at %s", request.getRemoteAddr() ,request.getMethod(), parameters, request.getRequestURI(), new Date());
		loggerService.getAccess().trace(requestMessage);
		//Log payload on non-sensitive requests
		if(!sensitive) {
			payload = getPayload();
			String body = String.format("%s invoked %s with parameters %s and payload %s", jp.getTarget(), jp.getSignature(), parameters, payload);
			loggerService.getAccess().trace(body);
		}
		try {
			result = jp.proceed();
		//Log Exceptions if anything goes wrong
		}catch (Throwable e) {
			String controlLog = jp.getTarget() + " invoked " + jp.getSignature() + " throwing: " + e;
			loggerService.getException().warn(controlLog, e);
			response.setStatus(500);
			throw e;
		}finally {
			//Log Response
			if(!sensitive) {
				String body = "";
				int status = response.getStatus();
				HttpStatus statusDescription = HttpStatus.resolve(status);
				if(result instanceof ArrayList) {
					body = ((ArrayList) result).toString();
				}else if (result != null){
					body = result.toString();
				}
				String responseMessage = String.format("Responded with status: %s and response: %s for %s request to %s by %s", 
						statusDescription, body, request.getMethod(), request.getRequestURI(), request.getRemoteAddr());
				loggerService.getAccess().trace(responseMessage);
			}
		}
		return result;
	}
	/**
	 * Sets up performance checking on all methods with the annotation LogExecutionTime
	 */
	@Around("@annotation(com.revature.annotations.Timed)")
	public Object logTime(ProceedingJoinPoint jp) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object result = jp.proceed();
		long timeTaken = System.currentTimeMillis() - startTime;
		String timeMsg = jp.getTarget() + " invoked " + jp.getSignature() + " taking " + timeTaken + " ms to run";
		if(timeTaken < 250) {
			loggerService.getPerformance().trace(timeMsg);
		}else if(timeTaken < 500) {
			loggerService.getPerformance().debug(timeMsg);
		}else if(timeTaken < 1000) {
			loggerService.getPerformance().info(timeMsg);
		}else if(timeTaken < 2000) {
			loggerService.getPerformance().warn(timeMsg);
		}
		return result;
	}
	/**
	 * Retrieves the payload of a request
	 * @return String payload
	 */
	private String getPayload() {
		if(request == null) return "";
		StringBuilder builder = new StringBuilder();
		ServletInputStream stream = null;
		BufferedReader reader = null;
		try {
			stream = request.getInputStream();
			//Spring uses the reader elsewhere so we have to do a deep copy to not block it
			reader = new BufferedReader(new InputStreamReader(stream));
			String line;
			while((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (IOException e) {
			loggerService.getException().warn("LoggingAspect failed to get request body", e);
		}
		return builder.toString();
	}
	private String buildRequestParameterSetring() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		Enumeration<String> parameterNames = request.getParameterNames();
		while(parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement().toString();
			builder.append(String.format("%s: %s", parameterName, request.getParameter(parameterName)));
			if(parameterNames.hasMoreElements()) {
				builder.append(", ");
			}
		}
		builder.append("]");
		return builder.toString();
	}
}