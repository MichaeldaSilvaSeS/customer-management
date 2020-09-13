package com.brasilprev.customermanagement.commons.usecase.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AddressDomain {
	
	private String thoroughfare;
	
	private Long number;
	
	private String complement;
	
	private String neighborhood;
	
	private String city;
	
	private String state;
	
	private String postalCode;
	
}
