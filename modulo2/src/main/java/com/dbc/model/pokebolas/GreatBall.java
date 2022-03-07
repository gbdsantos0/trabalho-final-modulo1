package com.dbc.model.pokebolas;

import com.dbc.model.entidades.Pokemon;
import com.dbc.model.interfaces.Pokebola;

public class GreatBall implements Pokebola {
    //multiplicador 1.5x
    @Override
    public Double calcularChance(Pokemon pokemon){
        return 1.5 * pokemon.getDificuldade().getChance();
    }
}
