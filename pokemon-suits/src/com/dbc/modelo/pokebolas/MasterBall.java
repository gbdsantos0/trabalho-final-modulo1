package com.dbc.modelo.pokebolas;

import com.dbc.modelo.entidades.Pokemon;
import com.dbc.modelo.interfaces.Pokebola;

public class MasterBall implements Pokebola {
    //sempre retorna 100% de chance
    @Override
    public Double calcularChance(Pokemon pokemon){
        return 100.0;
    }
}
