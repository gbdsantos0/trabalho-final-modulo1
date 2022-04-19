package com.pokesuits.pokebase.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokesuits.pokebase.dto.PokemonBaseDTO;
import com.pokesuits.pokebase.dto.pokeAgregateDTO;
import com.pokesuits.pokebase.enums.TipoPokemon;
import com.pokesuits.pokebase.service.PokemonBaseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pokemonbase")
@RequiredArgsConstructor
public class PokemonBaseController {
	private final PokemonBaseService baseService;
	
	@GetMapping("/todos-pokemonbase")
	public List<PokemonBaseDTO> todosPokemonbase(){
		return baseService.todosPokemonbase();
	}

	@GetMapping("/pokemon-por-tipo")
	public List<PokemonBaseDTO> getPokemonsPorTipo(@RequestParam TipoPokemon tipo) {
		return baseService.getByTipo(tipo.toString());
	}

	@GetMapping("/quantidade-pokemons-por-tipo")
	public List<pokeAgregateDTO> quantidadeDePokemonsPorTipo() {
		return baseService.quantidadePokemonsPorTipo();
	}
}
