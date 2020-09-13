package com.brasilprev.customermanagement.commons.dataprovider;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brasilprev.customermanagement.commons.dataprovider.entity.ClientEntity;
import com.brasilprev.customermanagement.commons.dataprovider.mapper.ClientDataProviderMapper;
import com.brasilprev.customermanagement.commons.usecase.domain.ClientDomain;
import com.brasilprev.customermanagement.create.usecase.gateway.CreateClientGateway;

@Repository
public interface ClientRepository extends CreateClientGateway, CrudRepository<ClientEntity, Long> {

	@Override
	default Long createClient(ClientDomain client) {
		return save(ClientDataProviderMapper.map(client)).getId();
	}
}
