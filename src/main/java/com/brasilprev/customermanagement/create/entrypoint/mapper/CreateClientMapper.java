package com.brasilprev.customermanagement.create.entrypoint.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import com.brasilprev.customermanagement.commons.entrypoint.dto.client.AddressDTO;
import com.brasilprev.customermanagement.commons.entrypoint.dto.client.ClientDTO;
import com.brasilprev.customermanagement.commons.entrypoint.dto.client.IdentityDocumentDTO;
import com.brasilprev.customermanagement.commons.usecase.domain.AddressDomain;
import com.brasilprev.customermanagement.commons.usecase.domain.ClientDomain;
import com.brasilprev.customermanagement.commons.usecase.domain.IdentityType;
import com.brasilprev.customermanagement.commons.usecase.domain.IdentityDocumentDomain;
import com.brasilprev.customermanagement.create.entrypoint.command.CreateClientCommandRequest;

public class CreateClientMapper {

	public static ClientDomain map(CreateClientCommandRequest createClientCommand) {
		ClientDTO client = createClientCommand.getClient();
		return ClientDomain.builder()
						.name(client.getName())
						.identificationDocument(mapIdentificationDocument(client.getIdentityDocument()))
						.address(mapAddress(client.getAddress()))
					.build();
	}

	private static Collection<AddressDomain> mapAddress(Collection<AddressDTO> addresses) {
		return addresses.stream()
				.map(address -> {
					String postalCode = address.getPostalCode().replaceAll("[-]", "");
					return AddressDomain.builder()
								.thoroughfare(address.getThoroughfare())
								.number(address.getNumber())
								.complement(address.getComplement())
								.neighborhood(address.getNeighborhood())
								.city(address.getCity())
								.state(address.getState())
								.postalCode(postalCode)
							.build();
				}).collect(Collectors.toList());
	}

	private static Collection<IdentityDocumentDomain> mapIdentificationDocument(
			Collection<IdentityDocumentDTO> identificationDocuments) {
		return identificationDocuments.stream()
				.map(document -> {
					String identification = document.getIdentity().replaceAll("[.-]", "");
					return IdentityDocumentDomain.builder()
								.identityType(IdentityType.valueOf(document.getIdentityType()))
								.identity(identification)
							.build();
				}).collect(Collectors.toList());
	}

}
