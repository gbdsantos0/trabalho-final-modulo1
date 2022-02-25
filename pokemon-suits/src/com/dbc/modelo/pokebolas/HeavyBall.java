package com.dbc.modelo.pokebolas;

import com.dbc.modelo.entidades.Pokemon;

public class HeavyBall extends Pokebola{
        @Override
        public Double calcularChance(Pokemon pokemon){
            switch ((int)(pokemon.getPeso()/102.35)){
                case 0:
                    return 0.75 * pokemon.getDificuldade().getChance();
                case 1:
                    return 0.75 * pokemon.getDificuldade().getChance();
                case 2:
                    return 1.0 * pokemon.getDificuldade().getChance();
                case 3:
                    return 1.5 * pokemon.getDificuldade().getChance();
                case 4:
                    return 2.0 * pokemon.getDificuldade().getChance();
                default:
                    throw new IllegalStateException("Unexpected value: " + pokemon.getPeso());
            }
        }
}
