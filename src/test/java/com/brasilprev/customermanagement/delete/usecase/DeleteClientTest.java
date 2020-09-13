package com.brasilprev.customermanagement.delete.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.brasilprev.customermanagement.commons.usecase.exception.DomainNotFoundException;
import com.brasilprev.customermanagement.delete.usecase.gateway.DeleteClientGateway;

@ExtendWith(SpringExtension.class)
public class DeleteClientTest {
	
	@InjectMocks
	private DeleteClientUseCase deleteClientUseCase;
	
	@Mock
	private DeleteClientGateway deleteClientGateway;
	
	@Test
	public void deleteClientFailureNotFound() {
		Assertions.assertThrows(DomainNotFoundException.class, () -> {
			Mockito.when(deleteClientGateway.existClientById(Mockito.any())).thenReturn(false);
			
			deleteClientUseCase.deleteClient(1L);
		});
		Mockito.verify(deleteClientGateway).existClientById(1L);
	}
	
	@Test
	public void deleteClientSucess() {
		Mockito.when(deleteClientGateway.existClientById(Mockito.any())).thenReturn(true);
		Mockito.doNothing().when(deleteClientGateway).deleteClientById(Mockito.any());
		
		deleteClientUseCase.deleteClient(1L);
		Mockito.verify(deleteClientGateway).existClientById(1L);
		Mockito.verify(deleteClientGateway).deleteClientById(1L);
	}

}
