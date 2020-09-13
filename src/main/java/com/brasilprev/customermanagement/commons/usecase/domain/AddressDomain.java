package com.brasilprev.customermanagement.commons.usecase.domain;

import lombok.Builder;
import lombok.Getter;

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
