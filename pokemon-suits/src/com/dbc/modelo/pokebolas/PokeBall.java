package com.dbc.modelo.pokebolas;

import com.dbc.modelo.entidades.Pokemon;
import com.dbc.modelo.interfaces.Pokebola;

public class PokeBall implements Pokebola {
    //pokebola com multiplicador base
    @Override
    public Double calcularChance(Pokemon pokemon){
        return 1.0 * pokemon.getDificuldade().getChance();
    }
}
