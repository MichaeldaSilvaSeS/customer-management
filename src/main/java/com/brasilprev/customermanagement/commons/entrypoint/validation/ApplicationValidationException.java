package com.brasilprev.customermanagement.commons.entrypoint.validation;

/**Class define the validation exceptions problems
* @author Michael da Silva
* @version 1.00
* @since Release 01
*/
public class ApplicationValidationException extends RuntimeException {
	private static final long serialVersionUID = 6893229156318949316L;
	
	public ApplicationValidationException(String message) {
		super(message);
	}

}
