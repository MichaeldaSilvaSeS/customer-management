package com.brasilprev.customermanagement.create.entrypoint.command;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.brasilprev.customermanagement.commons.entrypoint.dto.client.ClientDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CreateClientCommandRequest {

	@JsonProperty("client")
	@NotNull
	@Valid
	private ClientDTO client;

}
