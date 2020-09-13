package com.brasilprev.customermanagement.commons.dataprovider.mapper;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.brasilprev.customermanagement.commons.dataprovider.entity.AddressEntity;
import com.brasilprev.customermanagement.commons.dataprovider.entity.ClientEntity;
import com.brasilprev.customermanagement.commons.usecase.domain.ClientDomain;
import com.brasilprev.customermanagement.commons.usecase.domain.IdentityDocumentDomain;
import com.brasilprev.customermanagement.commons.usecase.domain.IdentityType;

@Component
public class ClientDataProviderMapper {

	public static ClientEntity map(ClientDomain clientDomain) {
		
		return ClientEntity.builder()
						.name(clientDomain.getName())
						.identityDocument(mapIdentityDocument(clientDomain.getIdentityDocument()))
						.address(mapAddress(clientDomain.getAddress()))
					.build();
	}

	private static AddressEntity mapAddress(
			Collection<com.brasilprev.customermanagement.commons.usecase.domain.AddressDomain> addresses) {
		
		return addresses.stream()
					.map(address -> {
						return AddressEntity.builder()
									.thoroughfare(address.getThoroughfare())
									.number(address.getNumber())
									.complement(address.getComplement())
									.neighborhood(address.getNeighborhood())
									.city(address.getCity())
									.state(address.getState())
									.postalCode(address.getPostalCode())
								.build();
					})
					.findFirst()
					.orElseThrow(() -> new RuntimeException("Identification Document not found"));
	}

	private static String mapIdentityDocument(
			Collection<IdentityDocumentDomain> identificationDocument) {
		
		return identificationDocument.stream()
				.filter(document -> {
					return document.getIdentityType() == IdentityType.CPF;
				})
				.map(document -> {
					return document.getIdentity();
				})
				.findAny()
				.orElseThrow(() -> new RuntimeException("Identification Document not found"));
	}

}
