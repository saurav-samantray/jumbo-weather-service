package com.met.jumbo.service;

import com.met.jumbo.dto.AddressSuggestion;

import java.util.Set;

public interface IAddressService {
    Set<AddressSuggestion> fetchAddrSuggestions();

}
