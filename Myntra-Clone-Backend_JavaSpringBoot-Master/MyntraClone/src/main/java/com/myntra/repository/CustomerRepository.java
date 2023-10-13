package com.myntra.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.myntra.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	Optional<Customer> findByEmail(String email);
}
