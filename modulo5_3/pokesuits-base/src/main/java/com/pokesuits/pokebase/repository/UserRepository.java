package com.pokesuits.pokebase.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pokesuits.pokebase.entity.UserEntity;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String>{
	Optional<UserEntity> findByUsername(String username);
}
