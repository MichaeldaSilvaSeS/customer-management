package com.brasilprev.customermanagement.search.entrypoint.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.brasilprev.customermanagement.commons.entrypoint.dto.client.AddressDTO;
import com.brasilprev.customermanagement.commons.entrypoint.dto.client.ClientDTO;
import com.brasilprev.customermanagement.commons.entrypoint.dto.client.IdentityDocumentDTO;
import com.brasilprev.customermanagement.commons.usecase.domain.AddressDomain;
import com.brasilprev.customermanagement.commons.usecase.domain.ClientDomain;
import com.brasilprev.customermanagement.commons.usecase.domain.IdentityDocumentDomain;

public class SearchClientMapper {

	public static List<ClientDTO> map(List<ClientDomain> listClientDomains) {
		return listClientDomains.stream()
					.map(clientDomain -> {
						return ClientDTO.builder()
										.id(clientDomain.getId())
										.name(clientDomain.getName())
										.addresses(mapAddress(clientDomain.getAddress()))
										.identityDocuments(mapIdentityDocument(clientDomain.getIdentityDocument()))
									.build();
					}).collect(Collectors.toList());
	}

	private static Collection<AddressDTO> mapAddress(Collection<AddressDomain> addresses) {
		return addresses.stream()
				.map(address -> {
					return AddressDTO.builder()
								.thoroughfare(address.getThoroughfare())
								.number(address.getNumber())
								.complement(address.getComplement())
								.neighborhood(address.getNeighborhood())
								.city(address.getCity())
								.state(address.getState())
								.postalCode(address.getPostalCode())
							.build();
				}).collect(Collectors.toList());
	}

	private static Collection<IdentityDocumentDTO> mapIdentityDocument(
			Collection<IdentityDocumentDomain> identificationDocuments) {
		
		return identificationDocuments.stream()
				.map(document -> {
					return IdentityDocumentDTO.builder()
								.identityType(document.getIdentityType().name())
								.identity(document.getIdentity())
							.build();
				}).collect(Collectors.toList());
	}

}
