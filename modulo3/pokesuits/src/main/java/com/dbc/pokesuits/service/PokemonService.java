package com.dbc.pokesuits.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbc.pokesuits.dto.pokemon.PokemonCreateDTO;
import com.dbc.pokesuits.dto.pokemon.PokemonDTO;
import com.dbc.pokesuits.model.entity.Pokemon;
import com.dbc.pokesuits.repository.PokemonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PokemonService {

	@Autowired
	private PokemonRepository pokemonRepository;
	@Autowired
	private ObjectMapper objectMapper;
	
	public List<PokemonDTO> ListarPokemons() {
		return pokemonRepository.list()
				.stream()
				.map(pokemon -> objectMapper.convertValue(pokemon, PokemonDTO.class))
				.collect(Collectors.toList());
	}

	public PokemonDTO AdicionarPokemon(PokemonCreateDTO createDTO) {
		
		Pokemon PokemonConvertido = objectMapper.convertValue(createDTO, Pokemon.class);
		
		Pokemon pokemonAtualizado = pokemonRepository.create(PokemonConvertido);
		
		PokemonDTO pokemonDTO = objectMapper.convertValue(pokemonAtualizado, PokemonDTO.class);
		
		return pokemonDTO;
	}

	public PokemonDTO RemoverPokemon(int id) {
		
		Pokemon pokemonRemovido = pokemonRepository.delete(id);
		
		PokemonDTO pokemonDTO = objectMapper.convertValue(pokemonRemovido, PokemonDTO.class);
		
		return pokemonDTO;
	}

	public PokemonDTO editarPokemon(PokemonCreateDTO createDTO, Integer id) {
		Pokemon PokemonConvertido = objectMapper.convertValue(createDTO, Pokemon.class);
		
		Pokemon pokemonAtualizado = pokemonRepository.update(id, PokemonConvertido);
		
		PokemonDTO pokemonDTO = objectMapper.convertValue(pokemonAtualizado, PokemonDTO.class);
		
		return pokemonDTO;
	}
}
