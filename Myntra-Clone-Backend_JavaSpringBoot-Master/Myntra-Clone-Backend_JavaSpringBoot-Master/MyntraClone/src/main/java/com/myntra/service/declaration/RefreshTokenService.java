package com.myntra.service.declaration;

import com.myntra.dto.StringInputDto;
import com.myntra.exception.MyntraException;

public interface RefreshTokenService {

	String getRefreshToken(String emailDto);

	String tokenValidation(StringInputDto refreshToken) throws MyntraException;

	void deleteToken(String input);
}
