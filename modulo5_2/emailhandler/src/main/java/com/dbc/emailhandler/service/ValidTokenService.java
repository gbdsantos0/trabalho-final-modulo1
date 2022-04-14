package com.dbc.emailhandler.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dbc.emailhandler.dto.EmailUser;
import com.dbc.emailhandler.dto.UserDTO;
import com.dbc.emailhandler.dto.ValidatedUser;
import com.dbc.emailhandler.entity.ValidationTokenEntity;
import com.dbc.emailhandler.repository.ValidationTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidTokenService {
	private final ValidationTokenRepository tokenRepository;
	private final EmailService emailService;
	
	
	@Scheduled(fixedRate = 3600000)
	private void checkExpirationDate() {
		List<ValidationTokenEntity> findAllByDataExpiracaoLessThanEqual = tokenRepository.findAllByDataExpiracaoLessThanEqual(LocalDateTime.now());
		
		findAllByDataExpiracaoLessThanEqual.stream().map(tok->{
			return ValidatedUser.builder().username(tok.getUsername()).valid(false).build();
		}).collect(Collectors.toList());
		
	}
	
	public void sendVerificationEmail(EmailUser emailUser) {
		
		ValidationTokenEntity save = tokenRepository.save(ValidationTokenEntity.builder()
				.dataExpiracao(LocalDateTime.now().plus(1,ChronoUnit.HOURS))
				.email(emailUser.getEmail())
				.Username(emailUser.getUsername()).build());
		
		emailService.sendEmail(new UserDTO(emailUser.getUsername(), emailUser.getUsername(), save.getId()), emailUser.getOperation(), emailUser.getEmail());
	}
	
}
