package com.met.jumbo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"test"})
class JumboWeatherServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
