package com.brasilprev.customermanagement.delete.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brasilprev.customermanagement.delete.usecase.gateway.DeleteClientGateway;

@Service
public class DeleteClientUseCase {
	
	@Autowired
	private DeleteClientGateway deleteClientGateway;

	public void deleteClient(Long clientId) {
		deleteClientGateway.deleteClientById(clientId);
	}

}
