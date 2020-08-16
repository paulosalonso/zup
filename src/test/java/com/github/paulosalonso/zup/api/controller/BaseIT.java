package com.github.paulosalonso.zup.api.controller;

import com.github.paulosalonso.zup.cleaner.DatabaseCleaner;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Disabled
@ExtendWith(SpringExtension.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseIT {
	
	@Autowired
	private DatabaseCleaner dbCleaner;
	
	@LocalServerPort
    private int port;

	@BeforeEach
	public void setUp() {
		RestAssured.port = port;
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		dbCleaner.clearTables();
	}
	
}