package com.dbc.modelo.pokebolas;


import com.dbc.modelo.enums.TipoPokemon;
import com.dbc.modelo.entidades.Pokemon;
import com.dbc.modelo.interfaces.Pokebola;

public class NetBall implements Pokebola {
    @Override
    public Double calcularChance(Pokemon pokemon){
        if(pokemon.getTipo()[0]==TipoPokemon.WATER||pokemon.getTipo()[0]==TipoPokemon.BUG||pokemon.getTipo()[1]==TipoPokemon.WATER||pokemon.getTipo()[1]==TipoPokemon.BUG){
            return 3.0 * pokemon.getDificuldade().getChance();
        }
        return 1.0 * pokemon.getDificuldade().getChance();
    }
}
