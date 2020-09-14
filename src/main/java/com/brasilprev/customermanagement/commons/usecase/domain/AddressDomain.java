package com.brasilprev.customermanagement.commons.usecase.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**Class define the client adrress
* @author Michael da Silva
* @version 1.00
* @since Release 01
*/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AddressDomain {
	
    /**
     * The street, avenue, etc
     */
	private String thoroughfare;
	
	private Long number;
	
    /**
     * Apartment
     */
	private String complement;
	
	private String neighborhood;
	
	private String city;
	
	private String state;
	
    /**
     * Brazilian CEP
     */
	private String postalCode;
	
}
