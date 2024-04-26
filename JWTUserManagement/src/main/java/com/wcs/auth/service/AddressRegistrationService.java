package com.wcs.auth.service;

import com.wcs.auth.dto.request.AddressRequest;
import com.wcs.auth.model.Address;

public interface AddressRegistrationService {
    Address saveAddress(AddressRequest addressRequest);
}
