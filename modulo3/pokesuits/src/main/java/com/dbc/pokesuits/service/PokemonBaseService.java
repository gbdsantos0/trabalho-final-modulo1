package com.dbc.pokesuits.service;

import com.dbc.pokesuits.repository.PokemonBaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonBaseService {
    @Autowired
    private PokemonBaseRepository pokemonBaseRepository;
    @Autowired
    private ObjectMapper objectMapper;
}
