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

@RestController
public class SearchClientEntrypoint {
	
	public static final String CREATE_CLIENT_URL = "/client";
	
	@Autowired
	private SearchClientUseCase searchClientUseCase;
	
	@GetMapping(value = CREATE_CLIENT_URL, params={"search=IDENTITY_DOCUMENT"}, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<List<ClientDTO>> searchByCPF(@RequestParam("identity") Optional<String> identity) {
		if(identity.isEmpty())
			throw new ApplicationValidationException("Identity param not found");
		
		List<ClientDTO> listOfClients = SearchClientMapper.map(searchClientUseCase.searchByCPF(identity.get()));
		
		return ResponseEntity
						.status(listOfClients.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK)
						.body(
								listOfClients
						);
	}

}
