package com.dbc.Controller;

import java.util.Random;

import com.dbc.model.entidades.Pokemon;
import com.dbc.model.entidades.Treinador;
import com.dbc.model.interfaces.Pokebola;
import com.dbc.repository.PokemonRepository;
import com.dbc.repository.TreinadorRepository;

public class TreinadorController {
    PokemonRepository pokemonRepository = new PokemonRepository();
    TreinadorRepository treinadorRepository = new TreinadorRepository();
    Random r = new Random();
    
    public boolean capturar(Pokebola pokebola, Pokemon pokemon, int idMochila){
            if(r.nextInt(100) <= pokebola.calcularChance(pokemon)){
            pokemon.setIdMochila(idMochila);
            try {
                pokemonRepository.adicionar(pokemon);
            } catch (Exception e){
            e.printStackTrace();
            }
            return true;
        }else {
            return false;
        }
    }

    public Treinador retornarTreinador(int id) {
    	Treinador treinador = null;
    	try {
			treinador = treinadorRepository.getById(1);
		}catch(Exception e){
			e.printStackTrace();
		}
    	return treinador;
    }

}
