package com.dbc.model.objetos;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.dbc.model.entidades.PokemonBase;

public class Pokedex {
    private Map<Integer, PokemonBase> pokedexCompleta;

    public Pokedex(Map<Integer, PokemonBase> pokedexCompleta) {
    	this.pokedexCompleta = new HashMap<>(pokedexCompleta);
    }

    public Map<Integer, PokemonBase> getPokedexCompleta() {
        return Collections.unmodifiableMap(this.pokedexCompleta);
    }
}
