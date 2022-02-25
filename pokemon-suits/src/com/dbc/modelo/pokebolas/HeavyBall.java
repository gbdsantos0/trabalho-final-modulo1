package com.dbc.modelo.pokebolas;

import com.dbc.modelo.seres_vivos.Pokemon;

public class HeavyBall extends Pokebola{
        @Override
        public Double calcularChance(Pokemon pokemon){
            switch ((int)(pokemon.getPeso()/102.35)){
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + pokemon.getPeso());
            }
            return 1.0;
        }
}
