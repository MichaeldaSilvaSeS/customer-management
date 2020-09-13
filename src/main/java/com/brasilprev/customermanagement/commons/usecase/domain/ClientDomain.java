package com.brasilprev.customermanagement.commons.usecase.domain;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ClientDomain {
	
	private Long id;
	
	private String name;
	
	private Collection<IdentityDocumentDomain> identityDocument;
	
	private Collection<AddressDomain> address;
	
}
