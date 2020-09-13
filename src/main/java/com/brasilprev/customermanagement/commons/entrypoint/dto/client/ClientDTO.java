package com.brasilprev.customermanagement.commons.entrypoint.dto.client;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ClientDTO {
	
	private Long id;
	
	@Length(min = 2, max = 197)
	@NotBlank
	private String name;
	
	@Size(min = 1, max = 1)
	@NotNull
	@Valid
	private Collection<IdentityDocumentDTO> identityDocuments;

	@Size(min = 1, max = 1)
	@NotNull
	@Valid
	private Collection<AddressDTO> addresses;	
	
}
