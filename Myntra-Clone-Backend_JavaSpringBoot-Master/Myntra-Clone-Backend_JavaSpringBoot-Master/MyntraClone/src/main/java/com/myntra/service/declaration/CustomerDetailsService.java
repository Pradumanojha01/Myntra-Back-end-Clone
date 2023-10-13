package com.myntra.service.declaration;

import java.util.List;
import com.myntra.dto.AddressDto;
import com.myntra.dto.CustomerDto;
import com.myntra.dto.StringInputDto;
import com.myntra.exception.MyntraException;

public interface CustomerDetailsService {

	CustomerDto customerDetails(String string);
	Boolean passwordVerify(StringInputDto stringInputDto) throws MyntraException;
	Boolean changePassword(StringInputDto stringInputDto) throws MyntraException;
	String getUser() throws MyntraException;
	List<AddressDto> getAddress() throws MyntraException;
	AddressDto addAddress(AddressDto addressDto) throws MyntraException;
	Boolean deleteAcc() throws MyntraException;
	CustomerDto editDetails(CustomerDto customerDto)throws MyntraException;
	AddressDto editAddress(AddressDto addressDto) throws MyntraException;

}
