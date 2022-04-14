package com.dbc.emailhandler.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document("token_entity")
public class ValidationTokenEntity {
	
	@Id
	private String id;
	private String Username;
	private String email;
	private LocalDateTime dataExpiracao;
	
}
