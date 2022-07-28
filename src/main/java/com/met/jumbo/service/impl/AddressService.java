package com.met.jumbo.service.impl;

import com.met.jumbo.dao.AddressDao;
import com.met.jumbo.dto.AddressSuggestion;
import com.met.jumbo.dto.SuggestionType;
import com.met.jumbo.service.IAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class AddressService implements IAddressService {

    @Autowired
    AddressDao dao;

    @Override
    public Set<AddressSuggestion> fetchAddrSuggestions() {
        return dao.findAll().stream()
                .flatMap(a -> Stream.of(
                        new AddressSuggestion(a.getCity(), SuggestionType.CITY),
                        new AddressSuggestion(a.getZipcode(), SuggestionType.ZIPCODE)))
                .collect(Collectors.toSet());
    }
}
