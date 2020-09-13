package com.brasilprev.customermanagement.commons.entrypoint.dto.validation;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FieldValidationErroDTO {

	private String field;
	
	private String error;
}
