package com.wcs.auth.repository;

import org.springframework.data.repository.CrudRepository;

import com.wcs.auth.model.Address;

public interface AddressRepository extends CrudRepository<Address, String> {

}
