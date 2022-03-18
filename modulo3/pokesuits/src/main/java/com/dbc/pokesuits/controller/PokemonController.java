package com.dbc.pokesuits.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbc.pokesuits.dto.pokemon.PokemonCreateDTO;
import com.dbc.pokesuits.dto.pokemon.PokemonDTO;
import com.dbc.pokesuits.service.PokemonService;

@RestController
@RequestMapping(path = "/pokemon")
public class PokemonController {
	@Autowired
	private PokemonService pokemonService;
	
	@GetMapping
	public List<PokemonDTO> ListarPokemons(){
		return pokemonService.ListarPokemons();
	}
	
	@PostMapping
	public PokemonDTO AdicionarPokemon(@RequestBody PokemonCreateDTO pokemon){
		return pokemonService.AdicionarPokemon(pokemon);
	}
	
	
	@DeleteMapping(path = "/{id}")
	public void RemoverPokemon(@PathVariable("id") int id) {
		pokemonService.RemoverPokemon(id);
	}
	@PutMapping
	public PokemonDTO editarPokemon(@RequestBody PokemonCreateDTO createDTO, Integer id) {
		return pokemonService.editarPokemon(createDTO, id);
	}
	
}
