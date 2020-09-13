package com.brasilprev.customermanagement.search.usecase.gateway;

import java.util.List;

import com.brasilprev.customermanagement.commons.usecase.domain.ClientDomain;

public interface SearchClientGateway {
	
	public List<ClientDomain> searchByCPF(String cpf);

}
