package com.met.jumbo.service.impl;

import com.met.jumbo.dao.AddressDao;
import com.met.jumbo.dto.AddressSuggestion;
import com.met.jumbo.dto.SuggestionType;
import com.met.jumbo.model.Address;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class AddressServiceTest {

    @Mock
    AddressDao dao;

    @InjectMocks
    AddressService service;

    @Test
    public void fetchSuggestion_success(){
        Set<AddressSuggestion> suggestions = service.fetchAddrSuggestions();
        log.debug("Mock Suggestions: {}", suggestions);
        assertEquals(4, suggestions.size());
        assertEquals(2, suggestions.stream().filter(as -> SuggestionType.ZIPCODE.equals(as.getType())).count());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Address a1 = new Address(1, "Sarjapur", "560035", "Bangalore", "Karnataka", "India");
        Address a2 = new Address(1, "Old Town", "751002", "Bhubaneswar", "Odisha", "India");
        Mockito.when(dao.findAll()).thenReturn(List.of(a1, a2));
    }
}