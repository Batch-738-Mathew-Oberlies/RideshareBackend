package com.revature.exceptions;

public class IllegalNullArgumentException extends RuntimeException {

	private static final long serialVersionUID = 4125780680251122019L;

	public IllegalNullArgumentException() {
		super();
	}

	public IllegalNullArgumentException(String arg0) {
		super(arg0);
	}
}