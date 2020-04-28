package com.revature.services;

import java.util.List;
import java.util.Optional;

import com.revature.models.Address;
import com.revature.models.AddressDTO;

public interface AddressService {
	List<Address> findAll();
	List<AddressDTO> findAllDTO();
	Address addAddress(Address address);
	Optional<Address> getAddressById(int id);
	Address updateAddress(Address address);
	void deleteAddressById(int id);
}
