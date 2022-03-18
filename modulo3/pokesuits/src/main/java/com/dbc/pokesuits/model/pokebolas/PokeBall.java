package com.dbc.pokesuits.model.pokebolas;

import com.dbc.model.entidades.Pokemon;
import com.dbc.model.interfaces.Pokebola;

public class PokeBall implements Pokebola {
    //pokebola com multiplicador base
    @Override
    public Double calcularChance(Pokemon pokemon){
        return 1.0 * pokemon.getDificuldade().getChance();
    }
}
