package com.brasilprev.customermanagement.commons.dataprovider.mapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.brasilprev.customermanagement.commons.dataprovider.entity.AddressEntity;
import com.brasilprev.customermanagement.commons.dataprovider.entity.ClientEntity;
import com.brasilprev.customermanagement.commons.usecase.domain.AddressDomain;
import com.brasilprev.customermanagement.commons.usecase.domain.ClientDomain;
import com.brasilprev.customermanagement.commons.usecase.domain.IdentityDocumentDomain;
import com.brasilprev.customermanagement.commons.usecase.domain.IdentityType;

public class ClientDomainMapper {

	public static List<ClientDomain> map(List<ClientEntity> clientsEntity) {
		
		return clientsEntity.stream()
				.map(clientEntity -> {
						return ClientDomain.builder()
										.id(clientEntity.getId())
										.name(clientEntity.getName())
										.identityDocument(mapIdentityDocument(clientEntity.getIdentityDocument()))
										.address(mapAddress(clientEntity.getAddress()))
									.build();
				}).collect(Collectors.toList());
	}

	private static List<AddressDomain> mapAddress(
			AddressEntity address) {
		
		return Arrays.asList(
				AddressDomain.builder()
					.thoroughfare(address.getThoroughfare())
					.number(address.getNumber())
					.complement(address.getComplement())
					.neighborhood(address.getNeighborhood())
					.city(address.getCity())
					.state(address.getState())
					.postalCode(address.getPostalCode())
				.build());
	}

	private static List<IdentityDocumentDomain> mapIdentityDocument(
			String identityDocument) {
		
		return Arrays.asList(
					IdentityDocumentDomain.builder()
							.identityType(IdentityType.CPF)
							.identity(identityDocument)
						.build()
				);
	}
}
