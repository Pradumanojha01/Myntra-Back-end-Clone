package com.myntra.service.impl;

import java.time.Instant;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.myntra.Constants;
import com.myntra.dto.StringInputDto;
import com.myntra.entity.JwtRefreshToken;
import com.myntra.entity.StringInput;
import com.myntra.exception.MyntraException;
import com.myntra.repository.RefreshTokenRepository;
import com.myntra.service.declaration.RefreshTokenService;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

	@Autowired
	RefreshTokenRepository refreshTokenRepository;

	@Override
	public String getRefreshToken(String emailDto) {
		String email = emailDto;
		JwtRefreshToken newToken = new JwtRefreshToken(email, UUID.randomUUID().toString(),
				Instant.now().plusMillis(Constants.REFRESH_TOKEN_VALIDITY));
		refreshTokenRepository.save(newToken);
		return newToken.getToken();
	}

	@Override
	public String tokenValidation(StringInputDto tokenDto) throws MyntraException {
		StringInput token = new StringInput(tokenDto.getInput());
		JwtRefreshToken refreshtoken = refreshTokenRepository.findByToken(token.getInput())
				.orElseThrow(() -> new MyntraException("TOKEN.NOT.FOUND", HttpStatus.BAD_REQUEST));
		if (refreshtoken.getExpirationDate().isAfter(Instant.now())) {
			return refreshtoken.getEmail();
		} else {
			refreshTokenRepository.deleteById(refreshtoken.getEmail());
			throw new MyntraException("TOKEN.INVALID", HttpStatus.BAD_REQUEST);
		}
	}
	
	

	@Scheduled(fixedDelay = Constants.FIXED_DELAY)
	private void cleanup() {
		refreshTokenRepository.findAll().forEach(token -> {
			if (token.getExpirationDate().isAfter(Instant.now())) {
				refreshTokenRepository.delete(token);
			}
		});
	}

	@Override
	public void deleteToken(String input) {
		if(refreshTokenRepository.findByToken(input).isPresent()) {
		refreshTokenRepository.deleteById(input);
		}
	}
}
