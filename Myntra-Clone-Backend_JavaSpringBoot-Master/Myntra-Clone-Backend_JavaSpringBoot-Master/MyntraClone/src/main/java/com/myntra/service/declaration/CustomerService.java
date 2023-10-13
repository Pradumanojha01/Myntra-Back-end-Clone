package com.myntra.service.declaration;

import com.myntra.dto.CustomerDto;
import com.myntra.dto.StringInputDto;
import com.myntra.exception.MyntraException;

public interface CustomerService {

	void registerNewCustomer(CustomerDto customerDto) throws MyntraException;

	String welcomeService(String email);

	Boolean isPresent(StringInputDto stringInputDto);

}
