package com.dbc.modelo.pokebolas;

import com.dbc.modelo.entidades.Pokemon;

public class NetBall extends Pokebola{
    @Override
    public Double calcularChance(Pokemon pokemon){
        return 1.0;
    }
}
