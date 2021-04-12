package com.revature.exceptions;

public class ItemNotFoundException extends RuntimeException {


	private static final long serialVersionUID = 3549509534127702699L;

	public ItemNotFoundException() {
		super();
	}

	public ItemNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ItemNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItemNotFoundException(String message) {
		super(message);
	}

	public ItemNotFoundException(Throwable cause) {
		super(cause);
	}	

}
