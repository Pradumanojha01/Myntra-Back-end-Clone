package com.myntra.service.declaration;

import com.myntra.dto.OtpDetailsDto;
import com.myntra.dto.StringInputDto;

public interface OtpService {

	Integer generateOtp(StringInputDto email);
	boolean validateOtp(OtpDetailsDto otpDetailsDto);
	void sendOtpByEmail(String email, String otp);

	
}
