package com.brasilprev.customermanagement.search.entrypoint;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.brasilprev.customermanagement.commons.entrypoint.dto.client.ClientDTO;
import com.brasilprev.customermanagement.commons.usecase.domain.AddressDomain;
import com.brasilprev.customermanagement.commons.usecase.domain.ClientDomain;
import com.brasilprev.customermanagement.commons.usecase.domain.IdentityDocumentDomain;
import com.brasilprev.customermanagement.commons.usecase.domain.IdentityType;
import com.brasilprev.customermanagement.search.entrypoint.SearchClientEntrypoint;
import com.brasilprev.customermanagement.search.usecase.SearchClientUseCase;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class SearchClientEntrypointTest  {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SearchClientUseCase searchClientUseCase;
	
	private static final String CLIENT_NAME = "MICHAEL";

	@Test	
	public void searchClientSucess() throws Exception {
			Mockito.when(searchClientUseCase.searchByCPF(Mockito.anyString())).thenReturn(Arrays.<ClientDomain>asList(createClientDomain()));
			
			ResultActions andExpect = mockMvc.perform(get(SearchClientEntrypoint.CREATE_CLIENT_URL)
								.contentType(MediaType.APPLICATION_JSON_VALUE)
								.queryParam(SearchClientEntrypoint.QUERY_PARAM_SEARCH, SearchClientEntrypoint.QUERY_PARAM_SEARCH_VALUE)
								.queryParam(SearchClientEntrypoint.QUERY_PARAM_IDENTITY, "53107862010")
							).andDo(print())
							.andExpect(status().is(HttpStatus.SC_OK));
			
			List<ClientDTO> listOfClients = new ObjectMapper().readValue(andExpect.andReturn().getResponse().getContentAsString(), new TypeReference<List<ClientDTO>>(){});
			
			Assertions.assertNotNull(listOfClients.size());
			Assertions.assertEquals(listOfClients.size(), 1);
			Assertions.assertEquals(listOfClients.get(0).getName(), CLIENT_NAME);
	}
	
	@Test	
	public void searchClientSearchMissingParam() throws Exception {
			Mockito.when(searchClientUseCase.searchByCPF(Mockito.anyString())).thenReturn(Arrays.<ClientDomain>asList(createClientDomain()));
			
			mockMvc.perform(get(SearchClientEntrypoint.CREATE_CLIENT_URL)
							.contentType(MediaType.APPLICATION_JSON_VALUE)
							.queryParam(SearchClientEntrypoint.QUERY_PARAM_SEARCH, SearchClientEntrypoint.QUERY_PARAM_SEARCH_VALUE)
						).andDo(print())
						.andExpect(status().is(HttpStatus.SC_BAD_REQUEST))
						.andExpect(MockMvcResultMatchers.jsonPath("$.description").value(SearchClientEntrypoint.QUERY_PARAM_MISSING_IDENTITY));
	}
	
	private ClientDomain createClientDomain() {
		return ClientDomain.builder()
						.id(1L)
						.name(CLIENT_NAME)
						.identityDocument(createIdentityDocuments())
						.address(createAddress())
					.build();
	}

	private Collection<AddressDomain> createAddress() {
		AddressDomain address =  AddressDomain.builder()
										.thoroughfare("thoroughfare")
						                .number(1L)
						                .complement("complement")
						                .neighborhood("neighborhood")
						                .city("city")
						                .state("state")
						                .postalCode("09100200")
									.build();
		return Arrays.<AddressDomain>asList(address);
	}

	private Collection<IdentityDocumentDomain> createIdentityDocuments() {
			IdentityDocumentDomain identity = IdentityDocumentDomain.builder()
														.identity("53107862010")
														.identityType(IdentityType.CPF)
													.build();
		return Arrays.<IdentityDocumentDomain>asList(identity);
	}

}
