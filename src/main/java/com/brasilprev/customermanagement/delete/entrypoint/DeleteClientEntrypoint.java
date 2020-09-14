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

import com.brasilprev.customermanagement.commons.entrypoint.dto.validation.ValidationErrorDTO;
import com.brasilprev.customermanagement.delete.usecase.DeleteClientUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
public class DeleteClientEntrypoint {
	
	public static final String CREATE_CLIENT_URL = "/client/";
	public static final String PATH_PARAM_CLIENT_ID = "client_id";
	
	@Autowired
	private DeleteClientUseCase deleteClientUseCase;
	
	@Operation(summary = "Delete client",
		parameters = {
			@Parameter(description = "Client id", required = true , example = "1", in = ParameterIn.PATH, name = PATH_PARAM_CLIENT_ID)
		},
		responses = {
			@ApiResponse(responseCode = "204", description = "Client deleted", content = @Content(schema = @Schema(implementation = Void.class))), 
			@ApiResponse(responseCode = "404", description = "Client not found", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class)))
		}
	)
	@DeleteMapping(value = CREATE_CLIENT_URL + "{" + PATH_PARAM_CLIENT_ID + "}", produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Void> deleteClient(
			@Valid @NotNull @Min(1) @PathVariable(PATH_PARAM_CLIENT_ID) Long clientId) {		
		deleteClientUseCase.deleteClient(clientId);
		
		return ResponseEntity
						.status(HttpStatus.NO_CONTENT)
						.body(null);
	}

}
