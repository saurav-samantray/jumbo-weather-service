package com.met.jumbo.rest;

import static com.met.jumbo.TestConstants.HOST;
import static org.junit.jupiter.api.Assertions.*;

import com.met.jumbo.dao.AddressDao;
import com.met.jumbo.dto.AddressSuggestion;
import com.met.jumbo.dto.SuggestionType;
import com.met.jumbo.model.Address;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.List;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test"})
@Slf4j
class AddressControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    AddressDao dao;

    @Test
    public void fetch_suggestions_success() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity response = restTemplate.exchange(
                HOST + port + "/api/address/suggestions", HttpMethod.GET, requestEntity, List.class);
        log.info("Response Body {}", response.getBody());
        assertEquals(3, ((List<HashMap>)response.getBody()).stream().filter(as -> SuggestionType.ZIPCODE.toString().equals(as.get("type"))).count());
        assertEquals(2, ((List<HashMap>)response.getBody()).stream().filter(as -> SuggestionType.CITY.toString().equals(as.get("type"))).count());
    }

    @Test
    public void fetch_suggestions_empty() throws Exception {
        dao.deleteAll();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity response = restTemplate.exchange(
                HOST + port + "/api/address/suggestions", HttpMethod.GET, requestEntity, List.class);
        log.info("Response Body {}", (List<AddressSuggestion>)response.getBody());
        assertEquals(0, ((List<AddressSuggestion>)response.getBody()).size());
    }

    @BeforeEach
    void setUp() {
        Address a1 = new Address(1, "Sarjapur Road", "560035", "Bangalore", "Karnataka", "India");
        dao.save(a1);
        Address a2 = new Address(2, "Bellandur", "560103", "Bangalore", "Karnataka", "India");
        dao.save(a2);
        Address a3 = new Address(3, "Old Town", "751002", "Bhubaneswar", "Odisha", "India");
        dao.save(a3);
    }

    @AfterEach
    void tearDown() {
        dao.deleteAll();
    }
}