package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
