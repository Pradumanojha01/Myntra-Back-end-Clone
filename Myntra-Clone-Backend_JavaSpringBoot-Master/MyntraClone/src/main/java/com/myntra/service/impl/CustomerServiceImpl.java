package com.myntra.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myntra.dto.CustomerDto;
import com.myntra.dto.StringInputDto;
import com.myntra.entity.CustomerAuth;
import com.myntra.entity.StringInput;
import com.myntra.entity.Customer;
import com.myntra.exception.MyntraException;
import com.myntra.repository.CustomerAuthRepository;
import com.myntra.repository.CustomerRepository;
import com.myntra.service.declaration.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CustomerAuthRepository customerAuthRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void registerNewCustomer(CustomerDto customerDto) throws MyntraException {

		boolean registeredEmail =customerRepository.findByEmail(customerDto.getEmail()).isEmpty();

		if (registeredEmail) {
			Customer customer = modelMapper.map(customerDto, Customer.class);
			customerRepository.save(customer);
			CustomerAuth customerAuth = new CustomerAuth();
			customerAuth.setEmail(customer.getEmail());
			customerAuth.setPassword(passwordEncoder.encode(customer.getPassword()));
			customerAuth.setAuthCustomer(customer);
			customerAuthRepository.save(customerAuth);
		} else {
			throw new MyntraException("EMAIL.ALREADY.EXISTS", HttpStatus.BAD_GATEWAY);
		}

	}

	@Override
	public String welcomeService(String email) {
		Customer customer = customerRepository.findByEmail(email).orElseThrow();
		return customer.getName();
	}

	@Override
	public Boolean isPresent(StringInputDto stringInputDto) {
		StringInput email = new StringInput(stringInputDto.getInput());
		if (customerRepository.findByEmail(email.getInput()).isPresent()) {
			return true;
		} else {
			return false;
		}
	}

}
