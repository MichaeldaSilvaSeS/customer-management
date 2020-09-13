package com.brasilprev.customermanagement.commons.dataprovider;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brasilprev.customermanagement.commons.dataprovider.entity.ClientEntity;
import com.brasilprev.customermanagement.commons.dataprovider.mapper.ClientDataProviderMapper;
import com.brasilprev.customermanagement.commons.dataprovider.mapper.ClientDomainMapper;
import com.brasilprev.customermanagement.commons.usecase.domain.ClientDomain;
import com.brasilprev.customermanagement.create.usecase.gateway.CreateClientGateway;
import com.brasilprev.customermanagement.search.usecase.gateway.SearchClientGateway;

@Repository
public interface ClientRepository extends CreateClientGateway, SearchClientGateway,  CrudRepository<ClientEntity, Long> {

	@Override
	default Long createClient(ClientDomain client) {
		return save(ClientDataProviderMapper.map(client)).getId();
	}
	
	@Query("SELECT c FROM ClientEntity c WHERE c.identityDocument = :cpf")
	List<ClientEntity> searchEntityByCPF(@Param("cpf") String cpf);
	
	default List<ClientDomain> searchByCPF(String cpf) {
		return ClientDomainMapper.map(searchEntityByCPF(cpf));
	}
	
}
