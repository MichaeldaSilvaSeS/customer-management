package com.brasilprev.customermanagement.create.entrypoint;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.brasilprev.customermanagement.commons.BasicIntegrationTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreateClientEntrypointTest extends BasicIntegrationTest {
	
	@Test	
	public void createClientFailureWithoutBody() {
		RestAssured.given()
						.contentType(ContentType.JSON)
						.post(buildURL(CreateClientEntrypoint.CREATE_CLIENT_URL))
						.then()
							.statusCode(HttpStatus.SC_BAD_REQUEST);
	}

	@Test
	public void createClientFailureValidationField() {
		RestAssured.given()
						.contentType(ContentType.JSON)
						.with()
							.body("{}")
						.post(buildURL(CreateClientEntrypoint.CREATE_CLIENT_URL))
						.then()
							.statusCode(HttpStatus.SC_BAD_REQUEST)
							.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/create_client/create_client_validation_error.json")
									.using(jsonSchemaFactory));
	}
	
	@Test
	public void createClientSuccess() throws IOException {
		RestAssured.given()
						.contentType(ContentType.JSON)
						.with()
							.body(loadBody("example/create_client/create_client_success.json"))
						.post(buildURL(CreateClientEntrypoint.CREATE_CLIENT_URL))
						.then()
							.statusCode(HttpStatus.SC_CREATED)
							.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/create_client/create_client_success.json")
									.using(jsonSchemaFactory))
							.body("client.client_id", Matchers.equalTo(1));
	}

}
