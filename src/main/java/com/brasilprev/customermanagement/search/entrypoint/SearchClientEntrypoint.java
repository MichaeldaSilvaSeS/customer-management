package com.brasilprev.customermanagement.search.entrypoint;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brasilprev.customermanagement.commons.entrypoint.dto.client.ClientDTO;
import com.brasilprev.customermanagement.commons.entrypoint.validation.ApplicationValidationException;
import com.brasilprev.customermanagement.search.entrypoint.mapper.SearchClientMapper;
import com.brasilprev.customermanagement.search.usecase.SearchClientUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class SearchClientEntrypoint {
	
	public static final String CREATE_CLIENT_URL = "/client";
	public static final String QUERY_PARAM_SEARCH = "search";
	public static final String QUERY_PARAM_SEARCH_VALUE = "IDENTITY_DOCUMENT";
	public static final String QUERY_PARAM_IDENTITY = "identity";
	public static final String QUERY_PARAM_MISSING_IDENTITY = "Missing param identity";
	
	@Autowired
	private SearchClientUseCase searchClientUseCase;
	
	@Operation(summary = "Search for client",
		parameters = {
				@Parameter(description = "Search type", required = true , example = QUERY_PARAM_SEARCH_VALUE, in = ParameterIn.QUERY, name = QUERY_PARAM_SEARCH),
				@Parameter(description = "Identity value for search", required = true , example = "53107862010", in = ParameterIn.QUERY, name = QUERY_PARAM_IDENTITY)
		},
		responses = {
				  @ApiResponse(responseCode = "204", description = "Any client found", content = @Content(schema = @Schema(implementation = Void.class))), 
				  @ApiResponse(responseCode = "200", description = "Client(s) found", content = @Content(schema = @Schema(implementation = ClientDTO.class)))
		}
	)
	@GetMapping(value = CREATE_CLIENT_URL, params={QUERY_PARAM_SEARCH+"="+QUERY_PARAM_SEARCH_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<List<ClientDTO>> searchByCPF(@RequestParam(QUERY_PARAM_IDENTITY) Optional<String> identity) {
		if(identity.isEmpty())
			throw new ApplicationValidationException(QUERY_PARAM_MISSING_IDENTITY);
		
		List<ClientDTO> listOfClients = SearchClientMapper.map(searchClientUseCase.searchByCPF(identity.get()));
		
		return ResponseEntity
						.status(listOfClients.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK)
						.body(
								listOfClients
						);
	}

}
