package com.brasilprev.customermanagement.delete.entrypoint;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.brasilprev.customermanagement.delete.usecase.DeleteClientUseCase;

@RestController
public class DeleteClientEntrypoint {
	
	public static final String CREATE_CLIENT_URL = "/client/";
	public static final String PATH_PARAM_CLIENT_ID = "client_id";
	
	@Autowired
	private DeleteClientUseCase deleteClientUseCase;
	
	@DeleteMapping(value = CREATE_CLIENT_URL + "{" + PATH_PARAM_CLIENT_ID + "}", produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<?> createClient(@Valid @NotNull @Min(1) @PathVariable(PATH_PARAM_CLIENT_ID) Long clientId) {		
		deleteClientUseCase.deleteClient(clientId);
		
		return ResponseEntity
						.status(HttpStatus.NO_CONTENT)
						.body(null);
	}

}
