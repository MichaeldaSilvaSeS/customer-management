package com.brasilprev.customermanagement.commons.entrypoint;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.brasilprev.customermanagement.commons.entrypoint.dto.validation.FieldValidationErroDTO;
import com.brasilprev.customermanagement.commons.entrypoint.dto.validation.ValidationErrorDTO;
import com.brasilprev.customermanagement.commons.entrypoint.validation.ApplicationValidationException;
import com.brasilprev.customermanagement.commons.usecase.exception.DomainNotFoundException;

@ControllerAdvice
public class ExceptionHandlerEntrypoint extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<FieldValidationErroDTO> fieldErros = exception
			.getBindingResult().getAllErrors().stream()
			.map((error) -> {
		        String fieldName = ((FieldError) error).getField();
		        String errorMessage = error.getDefaultMessage();
		        return FieldValidationErroDTO.builder()
		        				.field(fieldName)
		        				.error(errorMessage)
		        			.build();
		    }).collect(Collectors.toList());
		
		return ResponseEntity
				.badRequest()
				.body(
						ValidationErrorDTO.builder()
								.code(400)
								.fieldErros(fieldErros)
							.build()
				);
	}
	
	@ExceptionHandler({ApplicationValidationException.class})
	public ResponseEntity<Object> handleValidationErro(HttpServletResponse response, Exception ex) {
		return ResponseEntity
				.badRequest()
				.body(
						ValidationErrorDTO.builder()
								.code(400)
								.description(ex.getMessage())
								.fieldErros(Collections.emptyList())
							.build()
				); 
	}
	
	@ExceptionHandler({DomainNotFoundException.class})
	public ResponseEntity<Object> handleDomainException(HttpServletResponse response, Exception ex) {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(
						ValidationErrorDTO.builder()
							.code(404)
							.description("Domain not found")
							.fieldErros(Collections.emptyList())
						.build()
				);
	}

}
