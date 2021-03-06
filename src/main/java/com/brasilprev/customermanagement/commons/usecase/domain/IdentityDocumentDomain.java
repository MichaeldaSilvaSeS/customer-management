package com.brasilprev.customermanagement.commons.usecase.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**Class define the identity document information for clients 
* @author Michael da Silva
* @version 1.00
* @since Release 01
*/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class IdentityDocumentDomain {

	private IdentityType identityType;
	
	private String identity;
}
