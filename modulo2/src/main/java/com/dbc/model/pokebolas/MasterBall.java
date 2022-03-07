package com.dbc.model.pokebolas;

import com.dbc.model.entidades.Pokemon;
import com.dbc.model.interfaces.Pokebola;

public class MasterBall implements Pokebola {
    //sempre retorna 100% de chance
    @Override
    public Double calcularChance(Pokemon pokemon){
        return 100.0;
    }
}
