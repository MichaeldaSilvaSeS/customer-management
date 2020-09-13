package com.brasilprev.customermanagement.create.entrypoint;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brasilprev.customermanagement.create.entrypoint.command.CreateClientCommandRequest;
import com.brasilprev.customermanagement.create.entrypoint.command.CreateClientCommandResponse;
import com.brasilprev.customermanagement.create.entrypoint.mapper.CreateClientMapper;
import com.brasilprev.customermanagement.create.usecase.CreateClientUseCase;

@RestController
public class CreateClientEntrypoint {
	
	@Autowired
	private CreateClientUseCase createClientUseCase;
	
	@PostMapping(value = "/client", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	public CreateClientCommandResponse createClient(@Valid @RequestBody CreateClientCommandRequest createClient) {
		Long clientId = createClientUseCase.createClient(CreateClientMapper.map(createClient));
		
		return CreateClientCommandResponse.builder()
						.clientId(clientId)
					.build();
	}

}
