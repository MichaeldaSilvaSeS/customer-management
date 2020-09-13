package com.brasilprev.customermanagement.create.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brasilprev.customermanagement.commons.usecase.domain.ClientDomain;
import com.brasilprev.customermanagement.create.usecase.gateway.CreateClientGateway;


@Service
public class CreateClientUseCase {
	
	@Autowired
	private CreateClientGateway createClientGateway;
	
	public Long createClient(ClientDomain client) {
		return createClientGateway.createClient(client);
	}

}
