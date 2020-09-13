package com.brasilprev.customermanagement.create.entrypoint;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brasilprev.customermanagement.create.entrypoint.command.CreateClientCommandRequest;
import com.brasilprev.customermanagement.create.entrypoint.command.CreateClientCommandResponse;
import com.brasilprev.customermanagement.create.entrypoint.mapper.CreateClientMapper;
import com.brasilprev.customermanagement.create.usecase.CreateClientUseCase;

@RestController
public class CreateClientEntrypoint {
	
	public static final String CREATE_CLIENT_URL = "/client";
	
	@Autowired
	private CreateClientUseCase createClientUseCase;
	
	@PostMapping(value = CREATE_CLIENT_URL, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<CreateClientCommandResponse> createClient(@Valid @NotNull @RequestBody CreateClientCommandRequest createClient) {
		Long clientId = createClientUseCase.createClient(CreateClientMapper.map(createClient));
		
		return ResponseEntity
						.status(HttpStatus.CREATED)
						.body(
								CreateClientCommandResponse.builder()
										.clientId(clientId)
									.build()
						);
	}

}
