package com.brasilprev.customermanagement.commons.usecase.domain;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**Class define the client information
* @author Michael da Silva
* @version 1.00
* @since Release 01
*/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ClientDomain {
	
    /**
     * Unique identification of client
     */
	private Long id;
	
    /**
     * Complente name of client
     */
	private String name;
	
	private Collection<IdentityDocumentDomain> identityDocument;
	
	private Collection<AddressDomain> address;
	
}
