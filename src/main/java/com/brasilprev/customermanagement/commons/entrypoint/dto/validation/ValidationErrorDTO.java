package com.brasilprev.customermanagement.commons.entrypoint.dto.validation;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ValidationErrorDTO {

	private List<FieldValidationErroDTO> fieldErros;
	
}
