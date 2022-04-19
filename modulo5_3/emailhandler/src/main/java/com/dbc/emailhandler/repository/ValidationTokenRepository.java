package com.dbc.emailhandler.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dbc.emailhandler.entity.ValidationTokenEntity;

public interface ValidationTokenRepository extends MongoRepository<ValidationTokenEntity, String> {
	List<ValidationTokenEntity> findAllByDataExpiracaoLessThanEqual(LocalDateTime localDateTime);
}
