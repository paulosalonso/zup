package com.github.paulosalonso.zup.application.api;

import com.github.paulosalonso.zup.application.cleaner.DatabaseCleaner;
import com.github.paulosalonso.zup.domain.PostalCodeInfo;
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

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Disabled
@ExtendWith(SpringExtension.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(initializers = DatabaseInitializer.class)
public class BaseIT {

	protected static final String SAO_PAULO_IBGE_CODE = "3550308";
	protected static final String SAO_PAULO_POSTAL_CODE = "01001000";
	protected static final String SAO_PAULO_CITY_NAME = "São Paulo";
	protected static final String SP_STATE_INITIALS = "SP";

	protected static final String JOINVILLE_IBGE_CODE = "4209102";
	protected static final String JOINVILLE_POSTAL_CODE = "89201110";
	protected static final String JOINVILLE_CITY_NAME = "Joinville";
	protected static final String SC_STATE_INITIALS = "SC";

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

	protected void mockViaCepSaoPaulo() {
		PostalCodeInfo postalCodeInfo = PostalCodeInfo.of()
				.street("Praça da Sé")
				.stateInitials(SP_STATE_INITIALS)
				.postalCode(SAO_PAULO_POSTAL_CODE)
				.ibgeCode(SAO_PAULO_IBGE_CODE)
				.district("Sé")
				.complement("lado ímpar")
				.cityName(SAO_PAULO_CITY_NAME)
				.build();

		when(getPostalCodeInfoPort.getPostalCodeInfo(SAO_PAULO_POSTAL_CODE))
				.thenReturn(Optional.of(postalCodeInfo));
	}

	protected void mockViaCepJoinville() {
		PostalCodeInfo postalCodeInfo = PostalCodeInfo.of()
				.street("Rua Padre Carlos")
				.stateInitials(SC_STATE_INITIALS)
				.postalCode(JOINVILLE_POSTAL_CODE)
				.ibgeCode(JOINVILLE_IBGE_CODE)
				.district("Centro")
				.complement("")
				.cityName(JOINVILLE_CITY_NAME)
				.build();

		when(getPostalCodeInfoPort.getPostalCodeInfo(JOINVILLE_POSTAL_CODE))
				.thenReturn(Optional.of(postalCodeInfo));
	}

	protected void verifyViaCepSaoPaulo(int times) {
		verify(getPostalCodeInfoPort, times(times)).getPostalCodeInfo(SAO_PAULO_POSTAL_CODE);
	}

	protected void verifyViaCepJoinville(int times) {
		verify(getPostalCodeInfoPort, times(times)).getPostalCodeInfo(JOINVILLE_POSTAL_CODE);
	}
	
}
