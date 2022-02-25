package com.dbc.modelo.pokebolas;

import com.dbc.modelo.entidades.Pokemon;

public class GreatBall extends Pokebola{
    @Override
    public Double calcularChance(Pokemon pokemon){
        return 1.5 * pokemon.getDificuldade().getChance();
    }
}
