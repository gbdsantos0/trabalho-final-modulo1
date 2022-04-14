package com.dbc.emailhandler.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dbc.emailhandler.entity.ValidationTokenEntity;

public interface ValidationTokenService extends MongoRepository<ValidationTokenEntity, String> {

}
