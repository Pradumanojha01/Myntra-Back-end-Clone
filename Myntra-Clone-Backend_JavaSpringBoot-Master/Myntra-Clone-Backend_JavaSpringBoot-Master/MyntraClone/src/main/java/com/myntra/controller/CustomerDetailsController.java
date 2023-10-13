package com.myntra.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myntra.dto.AddressDto;
import com.myntra.dto.CustomerDto;
import com.myntra.dto.StringInputDto;
import com.myntra.exception.MyntraException;
import com.myntra.security.LogoutService;
import com.myntra.service.declaration.CustomerDetailsService;
import com.myntra.service.declaration.RefreshTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/my")
@Api(value = "Customer Details Controller : REST APIs")
public class CustomerDetailsController {

	@Autowired
	CustomerDetailsService customerDetailsService;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	RefreshTokenService refreshTokenService;
	@Autowired
	Environment environment;
	@Autowired
	LogoutService logoutService;

	@DeleteMapping("/delete-acc")
	@ApiOperation(value = "To delete user account", response = Boolean.class)
	public ResponseEntity<Boolean> deleteAcc()throws MyntraException {
		return new ResponseEntity<>(customerDetailsService.deleteAcc(),HttpStatus.OK);
	}
	
	@PostMapping("/logout")
	@ApiOperation(value = "user logout", response = String.class)
	public ResponseEntity<String> logoutApi(@RequestBody @NotBlank StringInputDto stringInputDto,HttpServletRequest request) {
		refreshTokenService.deleteToken(stringInputDto.getInput());
		logoutService.logout(request);
		return new ResponseEntity<>(environment.getProperty("LOGGED.OUT"), HttpStatus.OK);
	}
	
	@PostMapping("/relogin")
	@ApiOperation(value = "Password verification for already logged in user", response = Boolean.class)
	public ResponseEntity<Boolean> customerLoginApi(@RequestBody @NotBlank StringInputDto stringInputDto) throws MyntraException {
		return new ResponseEntity<>(customerDetailsService.passwordVerify(stringInputDto), HttpStatus.OK);
	}
	
	@PutMapping("/change-password")
	@ApiOperation(value = "To change user password", response = Boolean.class)
	public ResponseEntity<Boolean> passwordChange(@RequestBody @NotBlank StringInputDto stringInputDto)throws MyntraException {
		return new ResponseEntity<>(customerDetailsService.changePassword(stringInputDto),HttpStatus.OK);
	}
	
	@GetMapping("/acc-details")
	@ApiOperation(value = "To check user account details", response = CustomerDto.class)
	public ResponseEntity<CustomerDto> customerDetails(Principal principal){
		return new ResponseEntity<>(customerDetailsService.customerDetails(principal.getName()), HttpStatus.OK);
	}
	
	@PutMapping("/edit-acc-details")
	@ApiOperation(value = "To edit user acc details", response = CustomerDto.class)
	public ResponseEntity<CustomerDto> editDetails(@RequestBody CustomerDto customerDto) throws MyntraException{
		return new ResponseEntity<>(customerDetailsService.editDetails(customerDto), HttpStatus.OK);
	}
	
	@PostMapping("/add-address")
	@ApiOperation(value = "To add user addresses", response = AddressDto.class)
	public ResponseEntity<AddressDto> addAddress(@RequestBody AddressDto addressDto)throws MyntraException {
		return new ResponseEntity<>(customerDetailsService.addAddress(addressDto),HttpStatus.OK);
	}
	
	@GetMapping("/getAlladdress")
	@ApiOperation(value = "To get all user addresses for an account", response = AddressDto.class)
	public ResponseEntity<List<AddressDto>> getAddress()throws MyntraException {
		return new ResponseEntity<>(customerDetailsService.getAddress(),HttpStatus.OK);
	}
	
	@PutMapping("/edit-address")
	@ApiOperation(value = "To edit user address details", response = AddressDto.class)
	public ResponseEntity<AddressDto> editAddress(@RequestBody AddressDto addressDto) throws MyntraException{
		return new ResponseEntity<>(customerDetailsService.editAddress(addressDto), HttpStatus.OK);
	}
	
}
