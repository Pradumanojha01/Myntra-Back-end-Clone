package com.myntra.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.myntra.entity.CustomerAuth;
import com.myntra.repository.CustomerAuthRepository;
import com.myntra.security.CustomUserDetails;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	CustomerAuthRepository customerAuthRepository;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		CustomerAuth user = customerAuthRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("CUSTOMER.NOT.FOUND"));
		return Optional.of(user).map(CustomUserDetails::new).get();
	}

}
