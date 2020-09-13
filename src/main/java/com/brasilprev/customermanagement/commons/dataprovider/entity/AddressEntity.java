package com.brasilprev.customermanagement.commons.dataprovider.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class AddressEntity {
	
	@Column(name = "COL_THOROUGHFARE", length = 200)
	private String thoroughfare;
	
	@Column(name = "COL_NUMBER", length = 5)
	private Long number;
	
	@Column(name = "COL_COMPLEMENT", length = 60)
	private String complement;
	
	@Column(name = "COL_NEIGHBORHOOD", length = 60)
	private String neighborhood;
	
	@Column(name = "COL_CITY", length = 58)
	private String city;
	
	@Column(name = "COL_STATE", length = 60)
	private String state;
	
	@Column(name = "COL_POSTAL_CODE", length = 8)
	private String postalCode;
	
}
