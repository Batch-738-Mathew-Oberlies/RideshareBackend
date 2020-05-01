package com.revature.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Address;
import com.revature.models.AddressDTO;
import com.revature.repositories.AddressRepository;
import com.revature.services.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	private AddressRepository repository;

	@Autowired
	public void setRepository(AddressRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Address> findAll() {
		return this.repository.findAll();
	}
	
	@Override
	public List<AddressDTO> findAllDTO(){
		List<Address> addresses = this.findAll();
		List<AddressDTO> dtos = new ArrayList<>();
		for(Address address: addresses) {
			dtos.add(new AddressDTO(address));
		}
		return dtos;
	}

	@Override
	public Address addAddress(Address address) {
		return this.repository.save(address);
	}

	@Override
	public Optional<Address> getAddressById(int id) {
		return this.repository.findById(id);
	}

	@Override
	public Address updateAddress(Address address) {
		return this.repository.save(address);
	}

	@Override
	public void deleteAddressById(int id) {
		this.repository.deleteById(id);
	}

}
