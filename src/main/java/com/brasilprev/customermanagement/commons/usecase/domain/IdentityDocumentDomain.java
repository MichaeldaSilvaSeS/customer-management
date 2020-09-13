package com.brasilprev.customermanagement.commons.usecase.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IdentityDocumentDomain {

	private IdentityType identityType;
	private String identity;
}
