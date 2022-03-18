package com.dbc.pokesuits.model.objetos;

import com.dbc.pokesuits.model.entity.PokemonBase;

public class Pokedex {
    private Map<Integer, PokemonBase> pokedexCompleta;

    public Pokedex(Map<Integer, PokemonBase> pokedexCompleta) {
    	this.pokedexCompleta = new HashMap<>(pokedexCompleta);
    }

    public Map<Integer, PokemonBase> getPokedexCompleta() {
        return Collections.unmodifiableMap(this.pokedexCompleta);
    }
}
