package com.dbc.pokesuits.model.pokebolas;

import com.dbc.pokesuits.model.entity.Pokemon;
import com.dbc.pokesuits.model.interfaces.Pokebola;

public class GreatBall implements Pokebola {
    //multiplicador 1.5x
    @Override
    public Double calcularChance(Pokemon pokemon){
        return 1.5 * pokemon.getDificuldade().getChance();
    }
}
