package com.dbc.emailhandler.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.dbc.emailhandler.exception.BusinessRuleException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dbc.emailhandler.dto.EmailUserDTO;
import com.dbc.emailhandler.dto.UserDTO;
import com.dbc.emailhandler.dto.ValidatedUserDTO;
import com.dbc.emailhandler.entity.ValidationTokenEntity;
import com.dbc.emailhandler.repository.ValidationTokenRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidTokenService {
	private final ValidationTokenRepository tokenRepository;
	private final EmailService emailService;
	private final ValidatedUserProducerService producerService;
	
	
	@Scheduled(fixedRate = 60000)
	private void checkExpirationDate() throws JsonProcessingException {
		List<ValidationTokenEntity> findAllByDataExpiracaoLessThanEqual = tokenRepository.findAllByDataExpiracaoLessThanEqual(LocalDateTime.now());
		
		List<ValidatedUserDTO> collect = findAllByDataExpiracaoLessThanEqual.stream().map(tok->{
			return ValidatedUserDTO.builder().username(tok.getUsername()).valid(false).build();
		}).collect(Collectors.toList());
		
		producerService.sendMessage(collect);
		
		tokenRepository.deleteAll(findAllByDataExpiracaoLessThanEqual);
	}
	
	public void sendVerificationEmail(EmailUserDTO emailUser) {
		
		ValidationTokenEntity save = tokenRepository.save(ValidationTokenEntity.builder()
				.dataExpiracao(LocalDateTime.now().plus(1,ChronoUnit.MINUTES))
				.email(emailUser.getEmail())
				.username(emailUser.getUsername()).build());
		
		emailService.sendEmail(new UserDTO(emailUser.getName(), emailUser.getUsername(), save.getId()), emailUser.getOperation(), emailUser.getEmail());
	}

	public void validateUser(String token) throws Exception {
		ValidationTokenEntity tokenEntity = tokenRepository.findById(token).orElseThrow(() -> new BusinessRuleException("Token inválido"));
		ValidatedUserDTO validatedUserDTO = ValidatedUserDTO.builder()
				.username(tokenEntity.getUsername())
				.valid(true)
				.build();

		producerService.sendMessage(Arrays.asList(validatedUserDTO));

		tokenRepository.deleteById(token);
	}
	
}
