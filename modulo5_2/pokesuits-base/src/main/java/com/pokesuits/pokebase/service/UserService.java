package com.pokesuits.pokebase.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pokesuits.pokebase.entity.UserEntity;
import com.pokesuits.pokebase.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public Optional<UserEntity> findByUsername(String username) {
		log.info("Chamado findByUsername");
		return userRepository.findByUsername(username);
	}
}
