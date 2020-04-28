package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Address;
import com.revature.models.AddressDTO;
import com.revature.services.AddressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/address")
@CrossOrigin
@Api(tags = {"Address"})
public class AddressController {
	private AddressService addressService;

	@Autowired
	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}
	
	@ApiOperation(value = "Return all addresses", tags = {"Address"})
	@GetMapping
	public ResponseEntity<List<AddressDTO>> getAddresses(){
		List<AddressDTO> dtos = addressService.findAllDTO();
		if(dtos.isEmpty())return ResponseEntity.notFound().build();
		return ResponseEntity.ok(dtos);
	}
	
	@ApiOperation(value = "Add a new address", tags = {"Address"})
	@PostMapping
	public ResponseEntity<AddressDTO> addAddress(@Valid @RequestBody AddressDTO addressDTO){
		Address address = addressService.addAddress(new Address(addressDTO));
		if (address == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.ok(new AddressDTO(address));
	}
	
}