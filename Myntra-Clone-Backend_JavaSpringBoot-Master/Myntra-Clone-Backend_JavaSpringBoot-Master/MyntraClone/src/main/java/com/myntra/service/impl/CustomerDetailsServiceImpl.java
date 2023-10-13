package com.myntra.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myntra.dto.AddressDto;
import com.myntra.dto.CustomerDto;
import com.myntra.dto.StringInputDto;
import com.myntra.entity.Address;
import com.myntra.entity.Customer;
import com.myntra.entity.CustomerAuth;
import com.myntra.exception.MyntraException;
import com.myntra.repository.AddressRepository;
import com.myntra.repository.CustomerAuthRepository;
import com.myntra.repository.CustomerRepository;
import com.myntra.service.declaration.CustomerDetailsService;

@Service
@Transactional
public class CustomerDetailsServiceImpl implements CustomerDetailsService {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	CustomerAuthRepository customerAuthRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public String getUser() throws MyntraException {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	if (!(authentication instanceof AnonymousAuthenticationToken)) {
	    return authentication.getName();
	}else{
	    throw new MyntraException("CUSTOMER.NOT.FOUND",HttpStatus.NOT_FOUND);
	}
	}
	
	@Override
	public CustomerDto customerDetails(String email) {
		Customer customer = customerRepository.findByEmail(email).get();
		return modelMapper.map(customer,CustomerDto.class) ;
	}

	@Override
	public Boolean passwordVerify(StringInputDto stringInputDto) throws MyntraException {
		String dbPassword = customerAuthRepository.findById(getUser()).get().getPassword();
		return passwordEncoder.matches(stringInputDto.getInput(), dbPassword);
	}

	@Override
	public Boolean changePassword(StringInputDto stringInputDto) throws MyntraException {
		try {
		String newPassword = stringInputDto.getInput();
		CustomerAuth customer= customerAuthRepository.findById(getUser()).get();
		customer.setPassword(passwordEncoder.encode(newPassword));
		customerAuthRepository.save(customer);
		return true;
		} catch (Exception e) {
			throw new MyntraException("PASSWORD.UPDATE.ERROR",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public AddressDto addAddress(AddressDto addressDto) throws MyntraException {
		Address address=modelMapper.map(addressDto,Address.class);
		address.setUserIdEmail(getUser());
		address.setAddCustomer(customerRepository.findByEmail(getUser()).get());
		address= addressRepository.save(address);
		return modelMapper.map(address, AddressDto.class);
	}

	@Override
	public List<AddressDto> getAddress() throws MyntraException {
		List<Address> address= addressRepository.findAllByUserIdEmail(getUser());
		List<AddressDto> addDto=new ArrayList<>();
		for (Address address2 : address) {
			addDto.add(modelMapper.map(address2,AddressDto.class));
		}
		return addDto;
	}

	@Override
	public Boolean deleteAcc() throws MyntraException {
		customerRepository.delete(customerRepository.findByEmail(getUser()).get());
		return true;
	}

	@Override
	public CustomerDto editDetails(CustomerDto customerDto) throws MyntraException {
		Customer customer =modelMapper.map(customerDto,Customer.class);
		if(customerRepository.existsById(customer.getUserId())) {
		customerRepository.save(customer);
		return customerDto;
		}
		else {
			throw new MyntraException("INVALID.USER.ID", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public AddressDto editAddress(AddressDto addressDto) throws MyntraException {
		Address address=modelMapper.map(addressDto,Address.class);
		if(addressRepository.equals(address.getAddId())) {
			addressRepository.save(address);
		return addressDto;
		}
		else {
			throw new MyntraException("INVALID.ADDRESS.ID", HttpStatus.NOT_FOUND);
		}
	}

}
