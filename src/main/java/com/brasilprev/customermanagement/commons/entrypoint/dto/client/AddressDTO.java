package com.brasilprev.customermanagement.commons.entrypoint.dto.client;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AddressDTO {
	
	@Length(min = 2, max = 200)
	@NotBlank
	private String thoroughfare;
	
	@Min(1)
	@Max(5)
	@NotNull
	private Long number;
	
	@Length(min = 2, max = 60)
	@NotBlank
	private String complement;
	
	@Length(min = 2, max = 60)
	@NotBlank
	private String neighborhood;

	@Length(min = 2, max = 58)
	@NotBlank
	private String city;
	
	@Length(min = 2, max = 60)
	@NotBlank
	private String state;
	
	@Pattern(regexp = "\\d{5}-\\d{3}")
	private String postalCode;
	
}
