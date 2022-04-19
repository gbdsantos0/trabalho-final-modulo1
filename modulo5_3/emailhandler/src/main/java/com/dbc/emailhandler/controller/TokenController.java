package com.dbc.emailhandler.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbc.emailhandler.service.ValidTokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {
	private final ValidTokenService tokenService;
	
	@PostMapping("/validar/{token}")
	public void vailidarToken(@PathVariable("token") String token) throws Exception {
		tokenService.validateUser(token);
	}
}
