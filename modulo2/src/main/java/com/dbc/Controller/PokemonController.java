package com.dbc.Controller;

import java.util.List;

import com.dbc.model.entidades.Pokemon;
import com.dbc.repository.PokemonRepository;

public class PokemonController {
	
	private PokemonRepository pokemonRepository = new PokemonRepository();
	
	public List<Pokemon> ListarPokemons(){
<<<<<<< HEAD
		return null;
=======
		List<Pokemon> p = null;
		try {
			p = pokemonRepository.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public boolean AdicionarPokemon(Pokemon pokemon){
		boolean f = false;
		try {
			pokemonRepository.adicionar(pokemon);
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	public boolean RemoverPokemon(Integer id) {
		boolean f = false;
		try {
			pokemonRepository.remover(id);
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	public Boolean editarPokemon(String nome, Integer level, int id) {
		if(nome == null && level == null)return false;
		
		try {
			pokemonRepository.editarPokemonNomeLevel(nome, level, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
>>>>>>> 1b3a2a86314ecdababdb4c0203d01d233cb29828
	}
	
}
