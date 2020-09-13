package com.brasilprev.customermanagement.search.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brasilprev.customermanagement.commons.usecase.domain.ClientDomain;
import com.brasilprev.customermanagement.search.usecase.gateway.SearchClientGateway;

@Service
public class SearchClientUseCase {
	
	@Autowired
	private SearchClientGateway searchGateway;

	public List<ClientDomain> searchByCPF(String cpf) {
		return searchGateway.searchByCPF(cpf);
	}

}
