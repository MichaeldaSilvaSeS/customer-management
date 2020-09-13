package com.brasilprev.customermanagement.commons.entrypoint;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.brasilprev.customermanagement.commons.entrypoint.dto.validation.FieldValidationErroDTO;
import com.brasilprev.customermanagement.commons.entrypoint.dto.validation.ValidationErrorDTO;

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
								.fieldErros(fieldErros)
							.build()
				);
	}

}
