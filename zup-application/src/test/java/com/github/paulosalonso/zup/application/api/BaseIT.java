package com.github.paulosalonso.zup.application.api;

import com.github.paulosalonso.zup.application.cleaner.DatabaseCleaner;
import com.github.paulosalonso.zup.usecase.port.city.GetPostalCodeInfoPort;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Disabled
@ExtendWith(SpringExtension.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(initializers = DatabaseInitializer.class)
public class BaseIT {
	
	@LocalServerPort
    private int port;

	@Autowired
	private DatabaseCleaner dbCleaner;

	@MockBean
	protected GetPostalCodeInfoPort getPostalCodeInfoPort;

	@BeforeEach
	public void setUp() {
		RestAssured.port = port;
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		dbCleaner.clearTables();
	}
	
}
