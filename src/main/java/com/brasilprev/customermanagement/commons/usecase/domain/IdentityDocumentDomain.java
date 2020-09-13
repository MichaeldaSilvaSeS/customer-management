package com.brasilprev.customermanagement.commons.usecase.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class IdentityDocumentDomain {

	private IdentityType identityType;
	private String identity;
}
