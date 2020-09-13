package com.brasilprev.customermanagement.create.usecase.gateway;

import com.brasilprev.customermanagement.commons.usecase.domain.ClientDomain;

public interface CreateClientGateway {
	
	public Long createClient(ClientDomain client);
	
}
