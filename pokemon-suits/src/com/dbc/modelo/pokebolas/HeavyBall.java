package com.dbc.modelo.pokebolas;

import com.dbc.modelo.entidades.Pokemon;

public class HeavyBall extends Pokebola{
        @Override
        public Double calcularChance(Pokemon pokemon){
            return 1.0;
        }
}
