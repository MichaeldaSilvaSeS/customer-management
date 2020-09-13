package com.brasilprev.customermanagement.commons.entrypoint.validation;

public class ApplicationValidationException extends RuntimeException {
	private static final long serialVersionUID = 6893229156318949316L;
	
	public ApplicationValidationException(String message) {
		super(message);
	}

}
