package com.brasilprev.customermanagement.commons;

import java.io.InputStream;

import org.springframework.boot.web.server.LocalServerPort;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

public class BasicIntegrationTest {
	
	@LocalServerPort
	private int port;
	
	protected JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory
														.newBuilder()
														.setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(SchemaVersion.DRAFTV4).freeze())
														.freeze();

	protected String buildURL(String entrypointURL) {
		return "http://localhost:"+port+entrypointURL;
	}
	
	protected InputStream loadBody(String path) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
	}

}
