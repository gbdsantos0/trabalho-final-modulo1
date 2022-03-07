package com.dbc.modelo.objetos;

import com.dbc.modelo.entidades.Pokemon;
import com.dbc.modelo.entidades.PokemonBase;
import com.dbc.modelo.enums.Dificuldades;
import com.dbc.modelo.enums.Raridades;
import com.dbc.modelo.enums.TipoPokemon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Pokedex {
    private Map<Integer, PokemonBase> pokedexCompleta;

    public Pokedex(Map<Integer, PokemonBase> pokedexCompleta) {
        this.pokedexCompleta = new HashMap<>(pokedexCompleta);
    }

    public Map<Integer, PokemonBase> getPokedexCompleta() {
        return Collections.unmodifiableMap(this.pokedexCompleta);
    }
}
