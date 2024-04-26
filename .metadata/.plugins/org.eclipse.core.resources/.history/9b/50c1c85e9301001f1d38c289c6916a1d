package com.wcs.auth.service.Impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wcs.auth.dto.request.AddressRequest;
import com.wcs.auth.model.Address;
import com.wcs.auth.repository.AddressRepository;
;

@Slf4j
@Service
public class AddressRegistrationServiceImpl implements com.wcs.auth.service.AddressRegistrationService {
    @Autowired
   AddressRepository addressRepository;
    @Override
    public Address saveAddress(AddressRequest addressRequest) {
        log.info("Address request");
        Address address = Address.builder()
                .address(addressRequest.getAddress())
                .city(addressRequest.getCity())
                .state(addressRequest.getState())
                .pin(addressRequest.getPin())
                .UserId(addressRequest.getUserId())
                .contact(addressRequest.getContact())
                .build();
        address = addressRepository.save(address);
        log.info("User's address registered successfully with id :  {}", address.getUserId());
        return address;
    }
	
}
