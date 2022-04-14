package com.dbc.emailhandler.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbc.emailhandler.dto.EmailUserDTO;
import com.dbc.emailhandler.dto.Operation;
import com.dbc.emailhandler.service.ValidTokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {
	private final ValidTokenService tokenService;
	
	@PostMapping("/validar/{token}")
	public String vailidarToken(@PathVariable("token") String token) {
		System.out.println(token);
		return null;
	}
	
	@GetMapping("/test")
	public void testar() {
		tokenService.sendVerificationEmail(EmailUserDTO.builder().email("joaovjcode@gmail.com").name("joao").username("joao").operation(Operation.REGISTER).build());
	}
	
}
