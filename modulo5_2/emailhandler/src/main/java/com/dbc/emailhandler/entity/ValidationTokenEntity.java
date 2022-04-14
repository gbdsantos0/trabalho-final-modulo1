package com.dbc.emailhandler.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("")
public class ValidationTokenEntity {
	
	@Id
	private String id;
	private String Username;
	private String email;
	private LocalDateTime dataExpiracao;
	
}
