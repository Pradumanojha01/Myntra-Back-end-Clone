package com.myntra.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.myntra.entity.CustomerAuth;

public interface CustomerAuthRepository extends CrudRepository<CustomerAuth, String> {
	Optional<CustomerAuth> findByEmail(String email);
}
