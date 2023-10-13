package com.myntra.repository;

import org.springframework.data.repository.CrudRepository;

import com.myntra.entity.Address;
import java.util.List;


public interface AddressRepository extends CrudRepository<Address, Integer> {
	
	List<Address> findAllByUserIdEmail(String userIdEmail);
	Address findByName(String name);
}
