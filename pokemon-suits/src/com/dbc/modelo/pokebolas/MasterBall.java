package com.dbc.modelo.pokebolas;

import com.dbc.modelo.seres_vivos.Pokemon;

public class MasterBall extends Pokebola{
    @Override
    public Double calcularChance(Pokemon pokemon){
        return 100.0;
    }
}
