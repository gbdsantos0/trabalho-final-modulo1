package com.pokesuits.pokebase.controller;

import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokesuits.pokebase.dto.PokemonBaseDTO;
import com.pokesuits.pokebase.service.PokemonBaseService;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class AdminController {
private final PokemonBaseService baseService;
	
	@GetMapping("/todos-pokemonbase")
	public Page<PokemonBaseDTO> todosPokemonbase(@RequestParam(required = false) Integer pagina){
		return baseService.todosPokemonbase(pagina);
	}
}
