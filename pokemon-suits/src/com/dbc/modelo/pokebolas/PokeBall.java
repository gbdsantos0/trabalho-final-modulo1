package com.dbc.modelo.pokebolas;

import com.dbc.modelo.seres_vivos.Pokemon;

public class PokeBall extends Pokebola{
    @Override
    public Double calcularChance(Pokemon pokemon){
        return 1.0;
    }
}
