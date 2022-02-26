package com.dbc.modelo.pokebolas;

import com.dbc.modelo.entidades.Pokemon;
import com.dbc.modelo.interfaces.Pokebola;

public class GreatBall implements Pokebola {
    @Override
    public Double calcularChance(Pokemon pokemon){
        return 1.5 * pokemon.getDificuldade().getChance();
    }
}
