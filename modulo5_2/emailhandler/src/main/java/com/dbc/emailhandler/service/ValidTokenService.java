package com.dbc.emailhandler.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ValidTokenService {
	
	@Scheduled(fixedRate = 3600000)
	private void checkExpirationDate() {
		
	}
	
}
