package com.brasilprev.customermanagement.commons.usecase.domain;

import java.util.Collection;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClientDomain {
	
	private String name;
	
	private Collection<IdentityDocumentDomain> identificationDocument;
	
	private Collection<AddressDomain> address;
	
}
