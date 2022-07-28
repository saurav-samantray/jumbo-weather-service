package com.met.jumbo.rest;

import com.met.jumbo.dto.AddressSuggestion;
import com.met.jumbo.service.IAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class AddressController {

    @Autowired
    IAddressService service;

    @GetMapping("/suggestions")
    public ResponseEntity<List<AddressSuggestion>> autoCompleteSuggestions(){
        return new ResponseEntity(service.fetchAddrSuggestions(),  HttpStatus.OK);
    }
}
